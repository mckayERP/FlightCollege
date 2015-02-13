-- Jan 12, 2014 12:44:33 PM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Column SET IsMandatory='Y',Updated=TO_TIMESTAMP('2014-01-12 12:44:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=1001386
;

-- Jan 12, 2014 12:44:37 PM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_bankdepositline','C_Charge_ID','NUMERIC(10)',null,null)
;

-- Jan 12, 2014 12:44:37 PM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_bankdepositline','C_Charge_ID',null,'NOT NULL',null)
;

-- Jan 12, 2014 2:13:01 PM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Field SET DisplayLogic='@ChargeAmt@!0',Updated=TO_TIMESTAMP('2014-01-12 14:13:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=1001181
;

-- Jan 12, 2014 2:13:07 PM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Tab SET HasTree='N',Updated=TO_TIMESTAMP('2014-01-12 14:13:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=1000059
;

