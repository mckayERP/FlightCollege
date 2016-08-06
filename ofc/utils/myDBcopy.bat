@Title Copy Database after backup
@Rem $Id: myDBcopyTemplate.bat,v 1.4 2003/12/04 04:38:27 jjanke Exp $

@Echo Backing-up the ADempiere installation to Amazon and the NAS (T:/)

@Rem	The backup process creates the following files:
@Rem		%ADEMPIERE_HOME%\data\ExpDat.dmp
@Rem		%ADEMPIERE_HOME%\data\ExpDat.log
@Rem		%ADEMPIERE_HOME%\data\ExpDat.jar (containing the above)
@Rem    
@Rem    This batch file adds a datestamp and copies the file to offsite storage.

@ECHO OFF
REM Sets the proper date and time stamp with 24Hr Time for log file naming
REM convention

@SET HOUR=%time:~0,2%
@SET dtStamp9=%date:~6,4%%date:~3,2%%date:~0,2%_0%time:~1,1%%time:~3,2%%time:~6,2%
@SET dtStamp24=%date:~6,4%%date:~3,2%%date:~0,2%_%time:~0,2%%time:~3,2%%time:~6,2%

@if "%HOUR:~0,1%" == " " (SET DATETIME=%dtStamp9%) else (SET DATETIME=%dtStamp24%)

@Echo Creating ExpDat_%DATETIME%.jar

@Echo Copy backup to Amazon S3
@REM Using Amazon s3 storage with versioning so no need to include the datestamp.  
@REM Older copies are maintained according to the lifecycle rules set in the S3 account.
@REM See the s3 documentation on Amazon for information on setting the credentials

@aws s3 cp %ADEMPIERE_HOME%\data\ExpDat.jar s3://OFCBackup/ADempiere/ExpDat/

@Echo Make local copy
@REM Comment this line to prevent local storage of the backup.

@copy %ADEMPIERE_HOME%\data\ExpDat.jar "%ADEMPIERE_HOME%\data\ExpDat_%DATETIME%.jar"

@Echo Copy %ADEMPIERE_HOME%\data\ExpDat_%DATETIME%.jar to NAS (T:\)
@copy %ADEMPIERE_HOME%\data\ExpDat.jar "T:\6 - IT\Adempiere\Backups\ExpDat_%DATETIME%.jar"

@Echo Delete old on-site backups
pushd %ADEMPIERE_HOME%\data\
wscript "C:\Scripts\DeletesOlderThan.js" 20
popd

@Echo Delete old off-site backups
pushd "T:\6 - IT\Adempiere\Backups\"
wscript "C:\Scripts\DeletesOlderThan.js" 30
popd
