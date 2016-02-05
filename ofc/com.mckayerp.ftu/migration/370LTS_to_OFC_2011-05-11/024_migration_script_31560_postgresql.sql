-- Feb 13, 2014 3:41:43 PM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,1001308,1455,0,10,1000061,'Value2',TO_TIMESTAMP('2014-02-13 15:41:43','YYYY-MM-DD HH24:MI:SS'),0,'Value To','U',60,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Value To',0,TO_TIMESTAMP('2014-02-13 15:41:43','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 13, 2014 3:41:43 PM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=1001308 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 13, 2014 3:42:15 PM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Column SET Description='Value or value from if comparator is between ">-<"', FieldLength=60,Updated=TO_TIMESTAMP('2014-02-13 15:42:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=1001326
;

-- Feb 13, 2014 3:42:15 PM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Field SET Name='Value', Description='Value or value from if comparator is between ">-<"', Help=NULL WHERE AD_Column_ID=1001326 AND IsCentrallyMaintained='Y'
;

-- Feb 13, 2014 3:42:30 PM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('ad_impvalrule_cond','Value1','VARCHAR(60)',null,'NULL')
;

-- Feb 13, 2014 3:43:07 PM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
ALTER TABLE AD_ImpValRule_Cond ADD COLUMN Value2 VARCHAR(60) DEFAULT NULL 
;

-- Feb 13, 2014 3:45:21 PM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,1001308,1001073,0,1000056,TO_TIMESTAMP('2014-02-13 15:45:21','YYYY-MM-DD HH24:MI:SS'),0,'Value To',60,'U','Y','Y','Y','N','N','N','N','N','Value To',TO_TIMESTAMP('2014-02-13 15:45:21','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 13, 2014 3:45:21 PM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=1001073 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 13, 2014 3:45:32 PM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Field SET SeqNo=20,IsDisplayed='Y' WHERE AD_Field_ID=1001114
;

-- Feb 13, 2014 3:45:32 PM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=1001127
;

-- Feb 13, 2014 3:45:32 PM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=1001156
;

-- Feb 13, 2014 3:45:32 PM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=1001121
;

-- Feb 13, 2014 3:45:32 PM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=1001116
;

-- Feb 13, 2014 3:45:32 PM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=1001123
;

-- Feb 13, 2014 3:45:32 PM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=1001129
;

-- Feb 13, 2014 3:45:32 PM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=1001073
;

-- Feb 13, 2014 3:45:32 PM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=1001125
;

-- Feb 13, 2014 3:45:32 PM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=1001117
;

-- Feb 13, 2014 3:46:30 PM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Field SET DisplayLogic='@Operator@=''>-<''', IsSameLine='Y',Updated=TO_TIMESTAMP('2014-02-13 15:46:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=1001073
;

-- Feb 13, 2014 11:03:58 PM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('ad_impvalrule_action','AD_Column_From','VARCHAR(10)',null,'NULL')
;

-- Feb 13, 2014 11:20:42 PM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Field SET AD_Reference_ID=18, AD_Reference_Value_ID=251, AD_Val_Rule_ID=214,Updated=TO_TIMESTAMP('2014-02-13 23:20:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=1001146
;

-- Feb 13, 2014 11:21:02 PM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Column SET AD_Reference_ID=11, AD_Reference_Value_ID=NULL, IsUpdateable='N',Updated=TO_TIMESTAMP('2014-02-13 23:21:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=1001350
;

-- Feb 13, 2014 11:21:07 PM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('ad_impvalrule_action','AD_Column_From','NUMERIC(10)',null,'NULL')
;

-- Feb 13, 2014 11:25:01 PM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Column SET FieldLength=22,Updated=TO_TIMESTAMP('2014-02-13 23:25:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=1001350
;

-- Feb 13, 2014 11:25:26 PM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Column SET AD_Reference_ID=18, AD_Reference_Value_ID=251,Updated=TO_TIMESTAMP('2014-02-13 23:25:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=1001350
;

-- Feb 13, 2014 11:25:55 PM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Field SET AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL,Updated=TO_TIMESTAMP('2014-02-13 23:25:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=1001146
;

-- Feb 14, 2014 6:40:43 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Tab SET OrderByClause='SeqNo',Updated=TO_TIMESTAMP('2014-02-14 06:40:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=1000056
;

-- Feb 14, 2014 8:31:18 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Column SET IsUpdateable='Y',Updated=TO_TIMESTAMP('2014-02-14 08:31:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=1001350
;

-- Feb 14, 2014 8:59:31 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,1000003,0,TO_TIMESTAMP('2014-02-14 08:59:31','YYYY-MM-DD HH24:MI:SS'),0,'U','Y','The Condition line is missing a leading And or Or','I',TO_TIMESTAMP('2014-02-14 08:59:31','YYYY-MM-DD HH24:MI:SS'),0,'ImpVal-missingAndOr')
;

-- Feb 14, 2014 8:59:31 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=1000003 AND NOT EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Message_ID=t.AD_Message_ID)
;

-- Feb 14, 2014 10:31:51 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Column SET DefaultValue='@SQL=SELECT CASE WHEN @SeqNo@ = (SELECT COALESCE(MIN(SeqNo),0) FROM AD_ImpValRule_Cond WHERE AD_ImpVal_Rule_ID=@AD_ImpVal_Rule_ID@) THEN null  ELSE ''A'' END',Updated=TO_TIMESTAMP('2014-02-14 10:31:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=1001352
;

-- Feb 14, 2014 10:40:25 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Column SET IsMandatory='Y',Updated=TO_TIMESTAMP('2014-02-14 10:40:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=1001352
;

-- Feb 14, 2014 10:40:33 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('ad_impvalrule_cond','AndOr','VARCHAR(3)',null,null)
;

-- Feb 14, 2014 10:40:33 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('ad_impvalrule_cond','AndOr',null,'NOT NULL',null)
;

-- Feb 14, 2014 10:41:12 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Column SET IsMandatory='N',Updated=TO_TIMESTAMP('2014-02-14 10:41:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=1001352
;

-- Feb 14, 2014 11:06:35 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Column SET MandatoryLogic=NULL,Updated=TO_TIMESTAMP('2014-02-14 11:06:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=1001352
;

