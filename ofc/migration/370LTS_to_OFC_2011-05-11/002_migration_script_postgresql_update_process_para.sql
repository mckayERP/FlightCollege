-- Dec 14, 2013 11:07:34 PM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,DefaultValue,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,2168,0,221,1000309,20,'IsValidateOnly',TO_TIMESTAMP('2013-12-14 23:07:33','YYYY-MM-DD HH24:MI:SS'),0,'N','U',1,'Y','Y','Y','N','Only validate the data ',50,TO_TIMESTAMP('2013-12-14 23:07:33','YYYY-MM-DD HH24:MI:SS'),0)
;
UPDATE AD_Process_Para SET Description='Validate the date and do not process', Name='Only Validate Data',Updated=TO_TIMESTAMP('2013-12-14 23:08:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=1000309
;

-- Dec 14, 2013 11:07:34 PM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=1000309 AND NOT EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;

