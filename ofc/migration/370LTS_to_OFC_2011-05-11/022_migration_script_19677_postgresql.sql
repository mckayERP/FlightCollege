-- Feb 12, 2014 10:01:41 PM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_TIMESTAMP('2014-02-12 22:01:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=1001176
;

-- Feb 12, 2014 10:14:08 PM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Workflow SET AD_WF_Node_ID=1000003, IsValid='Y',Updated=TO_TIMESTAMP('2014-02-12 22:14:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Workflow_ID=1000000
;

-- Feb 13, 2014 7:05:45 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,1000002,0,TO_TIMESTAMP('2014-02-13 07:05:45','YYYY-MM-DD HH24:MI:SS'),0,'U','Y','The file was loaded successfully','I',TO_TIMESTAMP('2014-02-13 07:05:45','YYYY-MM-DD HH24:MI:SS'),0,'Loaded')
;

-- Feb 13, 2014 7:05:45 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=1000002 AND NOT EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Message_ID=t.AD_Message_ID)
;

-- Feb 13, 2014 7:13:30 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Column SET DefaultValue='@SQL=SELECT COALESCE(MAX(SeqNo),0)+10 AS DefaultValue FROM AD_ImpValRule_Cond WHERE AD_ImpVal_Rule_ID=@AD_ImpVal_Rule_ID@',Updated=TO_TIMESTAMP('2014-02-13 07:13:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=1001324
;

-- Feb 13, 2014 7:17:09 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
CREATE TABLE AD_ImpValRule_Cond (AD_Client_ID NUMERIC(10) NOT NULL, AD_Column_ID NUMERIC(10) DEFAULT NULL , AD_ImpVal_Rule_ID NUMERIC(10) NOT NULL, AD_ImpValRule_Cond_ID NUMERIC(10) NOT NULL, AD_Org_ID NUMERIC(10) NOT NULL, AndOr VARCHAR(3) DEFAULT NULL , Created TIMESTAMP NOT NULL, CreatedBy NUMERIC(10) NOT NULL, Help VARCHAR(2000) DEFAULT NULL , IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL, LeftBracket VARCHAR(3) DEFAULT NULL , Operator VARCHAR(5) DEFAULT NULL , RightBracket VARCHAR(3) DEFAULT NULL , SeqNo NUMERIC(10) DEFAULT NULL , Updated TIMESTAMP NOT NULL, UpdatedBy NUMERIC(10) NOT NULL, Value1 VARCHAR(20) DEFAULT NULL , CONSTRAINT AD_ImpValRule_Cond_Key PRIMARY KEY (AD_ImpValRule_Cond_ID))
;

-- Feb 13, 2014 7:38:57 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('ad_impvalrule_action','AD_Client_ID','NUMERIC(10)',null,null)
;

