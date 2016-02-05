-- Jan 9, 2014 8:46:25 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,1000162,214,TO_TIMESTAMP('2014-01-09 08:46:25','YYYY-MM-DD HH24:MI:SS'),0,'Manual Deposit','U','Y','Deposit',TO_TIMESTAMP('2014-01-09 08:46:25','YYYY-MM-DD HH24:MI:SS'),0,'G')
;

-- Jan 9, 2014 8:46:25 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=1000162 AND NOT EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Ref_List_ID=t.AD_Ref_List_ID)
;

-- Jan 9, 2014 8:49:18 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Val_Rule (AD_Client_ID,AD_Org_ID,AD_Val_Rule_ID,Code,Created,CreatedBy,EntityType,IsActive,Name,Type,Updated,UpdatedBy) VALUES (0,0,1000006,'AD_Ref_List.Value <> ''G''',TO_TIMESTAMP('2014-01-09 08:49:18','YYYY-MM-DD HH24:MI:SS'),0,'U','Y','Tender Type - not manual Deposit','S',TO_TIMESTAMP('2014-01-09 08:49:18','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jan 9, 2014 8:49:54 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Column SET AD_Val_Rule_ID=1000006,Updated=TO_TIMESTAMP('2014-01-09 08:49:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=5046
;

