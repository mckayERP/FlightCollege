
-- Feb 12, 2014 9:31:57 PM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,1001304,1384,0,19,1000063,'C_Payment_ID',TO_TIMESTAMP('2014-02-12 21:31:57','YYYY-MM-DD HH24:MI:SS'),0,'Payment identifier','U',22,'The Payment is a unique identifier of this payment.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Payment',0,TO_TIMESTAMP('2014-02-12 21:31:57','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 12, 2014 9:31:57 PM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=1001304 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 12, 2014 9:39:46 PM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
ALTER TABLE C_BankDeposit ADD COLUMN C_Payment_ID NUMERIC(10) DEFAULT NULL 
;

-- Feb 12, 2014 9:41:35 PM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,1000000,0,TO_TIMESTAMP('2014-02-12 21:41:35','YYYY-MM-DD HH24:MI:SS'),0,'U','Y','Last Query','I',TO_TIMESTAMP('2014-02-12 21:41:35','YYYY-MM-DD HH24:MI:SS'),0,'Last Query')
;

-- Feb 12, 2014 9:41:36 PM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=1000000 AND NOT EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Message_ID=t.AD_Message_ID)
;

-- Feb 12, 2014 9:41:57 PM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,1000001,0,TO_TIMESTAMP('2014-02-12 21:41:57','YYYY-MM-DD HH24:MI:SS'),0,'U','Y','New Query','I',TO_TIMESTAMP('2014-02-12 21:41:57','YYYY-MM-DD HH24:MI:SS'),0,'New Query')
;

-- Feb 12, 2014 9:41:57 PM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=1000001 AND NOT EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Message_ID=t.AD_Message_ID)
;

-- Feb 12, 2014 9:49:41 PM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Column SET IsMandatory='N', IsUpdateable='N',Updated=TO_TIMESTAMP('2014-02-12 21:49:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=1001367
;

-- Feb 12, 2014 9:52:17 PM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Column SET IsMandatory='N',Updated=TO_TIMESTAMP('2014-02-12 21:52:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=1001386
;

-- Feb 12, 2014 9:52:28 PM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_bankdepositline','C_Charge_ID','NUMERIC(10)',null,'NULL')
;

-- Feb 12, 2014 9:52:28 PM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_bankdepositline','C_Charge_ID',null,'NULL',null)
;

-- Feb 12, 2014 9:55:02 PM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Column SET IsUpdateable='Y', ReadOnlyLogic='TRUE',Updated=TO_TIMESTAMP('2014-02-12 21:55:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=1001367
;

