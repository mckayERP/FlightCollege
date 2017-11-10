@Title Copy Database after backup
@Rem $Id: myDBcopyTemplate.bat,v 1.4 2003/12/04 04:38:27 jjanke Exp $

@Echo Modify the script myDBcopy to copy the database backup

@Rem    This example creates one unique file per day in a month
@Rem    You may want to copy it to another disk first
@Rem    Note that the %DATE% parameter is local specific.
@Rem    In Germany, it is %DATE:~3,2%
@Rem    When called, the following files were created:
@Rem        %ADEMPIERE_HOME%\data\ExpDat.dmp
@Rem        %ADEMPIERE_HOME%\data\ExpDat.log
@Rem        %ADEMPIERE_HOME%\data\ExpDat.jar (containing the above)

@SET HOUR=%time:~0,2%
@SET dtStamp9=%date:~6,4%%date:~3,2%%date:~0,2%_0%time:~1,1%%time:~3,2%%time:~6,2%
@SET dtStamp24=%date:~6,4%%date:~3,2%%date:~0,2%_%time:~0,2%%time:~3,2%%time:~6,2%

@if "%HOUR:~0,1%" == " " (SET DATETIME=%dtStamp9%) else (SET DATETIME=%dtStamp24%)

@Echo Creating ExpDat_%DATETIME%.jar

@Echo Make local copy
@REM Comment this line to prevent local storage of the backup.
@copy ExpDat.jar "ExpDat%DATETIME%.jar"

@Echo copy %ADEMPIERE_HOME%\data\ExpDat%DATETIME%.jar to backup media
@copy %ADEMPIERE_HOME%\data\ExpDat.jar "T:\6 - IT\Adempiere\Backups\ExpDat%DATETIME%.jar"
@REM @copy /Y %ADEMPIERE_HOME%\data\ExpDat.jar "%ADEMPIERE_HOME%\data\amazon\ExpDat.jar"

@REM "C:\Program Files\SprightlySoft\S3 Sync\S3Sync.exe" -AWSAccessKeyId AKIAI6LL33JEFAEWYRDQ -AWSSecretAccessKey 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 -BucketName OFCBackup -S3FolderKeyName ADempiere/ -SyncDirection Upload -LocalFolderPath C:\ADempiere\data\amazon -DeleteS3ItemsWhereNotInLocalList false -LogOnlyMode false -OutputLevel 2 -CompareFilesBy ETag -LogFilePath "C:\Documents and Settings\Administrator\Local Settings\Temp\1\S3SyncLog.txt"
@REM Using Amazon s3 storage with versioning.  Older copies maintained according to lifecycle rules set in the S3 account.
@REM @aws s3 cp ExpDat.jar s3://OFCBackup/ADempiere/ExpDat/

@REM Using Google Drive
@copy /Y %ADEMPIERE_HOME%\data\ExpDat.jar "C:\Users\admin\Google Drive\backup\adempiere\data\ExpDat%DATETIME%.jar"

@REM @Echo Delete old off-site backups
@REM cd "C:\My Dropbox\ADempiere Backups\"
cd "C:\Users\admin\Google Drive\backup\adempiere\data\"
wscript "C:\Scripts\DeletesOlderThan.js" 60

@Echo Delete old on-site backups
cd %ADEMPIERE_HOME%\data\
wscript "C:\Scripts\DeletesOlderThan.js" 20

@Echo Delete old T:\ drive backups
cd "T:\6 - IT\Adempiere\Backups\"
wscript "C:\Scripts\DeletesOlderThan.js" 30
