-- Dec 21, 2013 8:34:59 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('ad_impvalrule_action','AD_Column_From','VARCHAR(10)',null,'NULL')
;

-- Dec 21, 2013 8:53:27 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Column SET DefaultValue='@SQL=SELECT NVL(MAX(SeqNo),0)+10 AS DefaultValue FROM AD_ImpValRule_Action WHERE AD_ImpValRule_ID=@AD_ImpValRule_ID@',Updated=TO_TIMESTAMP('2013-12-21 08:53:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=1001344
;

-- Dec 21, 2013 8:53:57 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Column SET DefaultValue='@SQL=SELECT NVL(MAX(SeqNo),0)+10 AS DefaultValue FROM AD_ImpValRule_Action WHERE AD_ImpVal_Rule_ID=@AD_ImpVal_Rule_ID@',Updated=TO_TIMESTAMP('2013-12-21 08:53:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=1001344
;

-- Dec 21, 2013 8:55:12 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Column SET DefaultValue='@SQL=SELECT NVL(MAX(SeqNo),0)+10 AS DefaultValue FROM AD_ImpValRule_Cond WHERE AD_ImpVal_Rule_ID=@AD_ImpVal_Rule_ID@',Updated=TO_TIMESTAMP('2013-12-21 08:55:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=1001324
;

-- Dec 21, 2013 9:09:56 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Column SET AD_Reference_ID=18, AD_Reference_Value_ID=251,Updated=TO_TIMESTAMP('2013-12-21 09:09:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=1001321
;

-- Dec 21, 2013 9:10:01 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('ad_impvalrule_cond','AD_Column_ID','NUMERIC(10)',null,'NULL')
;

-- Dec 21, 2013 9:12:12 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('ad_impvalrule_action','AD_Column_From','VARCHAR(10)',null,'NULL')
;

