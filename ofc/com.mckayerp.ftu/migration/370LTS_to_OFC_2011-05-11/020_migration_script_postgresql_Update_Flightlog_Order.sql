-- Feb 9, 2014 9:09:58 PM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Reference (AD_Client_ID,AD_Org_ID,AD_Reference_ID,Created,CreatedBy,Description,EntityType,IsActive,IsOrderByValue,Name,Updated,UpdatedBy,ValidationType) VALUES (0,0,1000068,TO_TIMESTAMP('2014-02-09 21:09:58','YYYY-MM-DD HH24:MI:SS'),0,'Finds C_Order_IDs for a given OFC_Flightsheet_ID','U','Y','N','RelType C_Order (Sales) <= OFC Flightsheet',TO_TIMESTAMP('2014-02-09 21:09:58','YYYY-MM-DD HH24:MI:SS'),0,'T')
;

-- Feb 9, 2014 9:09:58 PM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Reference_Trl (AD_Language,AD_Reference_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Reference_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Reference t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=1000068 AND NOT EXISTS (SELECT * FROM AD_Reference_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Reference_ID=t.AD_Reference_ID)
;

-- Feb 9, 2014 9:10:33 PM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Ref_Table (AD_Client_ID,AD_Display,AD_Key,AD_Org_ID,AD_Reference_ID,AD_Table_ID,Created,CreatedBy,EntityType,IsActive,IsValueDisplayed,Updated,UpdatedBy) VALUES (0,2169,2161,0,1000068,259,TO_TIMESTAMP('2014-02-09 21:10:33','YYYY-MM-DD HH24:MI:SS'),0,'U','Y','N',TO_TIMESTAMP('2014-02-09 21:10:33','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 9, 2014 9:13:02 PM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Ref_Table SET OrderByClause='documentno', WhereClause='C_Order_ID IN (
  select o.c_order_id from c_order o
    left join ofc_flightsheet fs on o.c_order_id = fs.c_order_id
  where fs.OFC_Flightsheet_ID=@OFC_Flightsheet_ID@ AND o.isSOTrx=''Y''
)',Updated=TO_TIMESTAMP('2014-02-09 21:13:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=1000068
;

-- Feb 9, 2014 9:13:39 PM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Ref_Table SET AD_Window_ID=1000010,Updated=TO_TIMESTAMP('2014-02-09 21:13:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=1000068
;

-- Feb 9, 2014 9:16:29 PM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Reference (AD_Client_ID,AD_Org_ID,AD_Reference_ID,Created,CreatedBy,Description,EntityType,IsActive,IsOrderByValue,Name,Updated,UpdatedBy,ValidationType) VALUES (0,0,1000069,TO_TIMESTAMP('2014-02-09 21:16:29','YYYY-MM-DD HH24:MI:SS'),0,'Finds OFC_Flightsheet_ID for a given C_Order_ID','U','Y','N','RelType OFC_Flightsheet <= C_Order_ID (Sales)',TO_TIMESTAMP('2014-02-09 21:16:29','YYYY-MM-DD HH24:MI:SS'),0,'T')
;

-- Feb 9, 2014 9:16:29 PM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Reference_Trl (AD_Language,AD_Reference_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Reference_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Reference t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=1000069 AND NOT EXISTS (SELECT * FROM AD_Reference_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Reference_ID=t.AD_Reference_ID)
;

-- Feb 9, 2014 9:17:31 PM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Ref_Table (AD_Client_ID,AD_Display,AD_Key,AD_Org_ID,AD_Reference_ID,AD_Table_ID,Created,CreatedBy,EntityType,IsActive,IsValueDisplayed,Updated,UpdatedBy) VALUES (0,1000427,1000424,0,1000069,1000019,TO_TIMESTAMP('2014-02-09 21:17:31','YYYY-MM-DD HH24:MI:SS'),0,'U','Y','N',TO_TIMESTAMP('2014-02-09 21:17:31','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 9, 2014 9:18:53 PM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Ref_Table SET AD_Window_ID=1000009, OrderByClause='ofc_flight_date', WhereClause='C_Order_ID=@C_Order_ID@',Updated=TO_TIMESTAMP('2014-02-09 21:18:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=1000069
;

-- Feb 9, 2014 9:19:35 PM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Window SET IsSOTrx='Y',Updated=TO_TIMESTAMP('2014-02-09 21:19:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Window_ID=1000009
;

