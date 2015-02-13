-- Dec 14, 2013 11:21:55 PM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_ModelValidator (AD_Client_ID,AD_ModelValidator_ID,AD_Org_ID,Created,CreatedBy,Description,EntityType,IsActive,ModelValidationClass,Name,SeqNo,Updated,UpdatedBy) VALUES (0,1000000,0,TO_TIMESTAMP('2013-12-14 23:21:55','YYYY-MM-DD HH24:MI:SS'),0,'Bank Statement Import Validator','U','Y','org.adempiere.model.MyValidator.java','MyValidator',0,TO_TIMESTAMP('2013-12-14 23:21:55','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Dec 14, 2013 11:22:07 PM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_ModelValidator SET ModelValidationClass='org.adempiere.model.MyValidator',Updated=TO_TIMESTAMP('2013-12-14 23:22:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_ModelValidator_ID=1000000
;

-- Dec 14, 2013 11:22:23 PM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_ModelValidator SET SeqNo=2,Updated=TO_TIMESTAMP('2013-12-14 23:22:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_ModelValidator_ID=1000000
;

-- Dec 14, 2013 11:22:57 PM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_ModelValidator SET Name='My Validator for Bank Statement Imports',Updated=TO_TIMESTAMP('2013-12-14 23:22:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_ModelValidator_ID=1000000
;

