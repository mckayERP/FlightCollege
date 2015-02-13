-- Dec 26, 2013 9:14:40 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Column SET FieldLength=2000,Updated=TO_TIMESTAMP('2013-12-26 09:14:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=1001351
;

-- Dec 26, 2013 9:14:44 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('ad_impvalrule_action','NewValue','VARCHAR(2000)',null,'NULL')
;

