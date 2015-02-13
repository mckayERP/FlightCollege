-- Dec 14, 2013 12:21:17 PM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,1000312,0,'BankID',TO_TIMESTAMP('2013-12-14 12:21:17','YYYY-MM-DD HH24:MI:SS'),0,'The Bank ID number used in OFX and or EFT transactions.  Used to match import data with the bank.','U','The Bank ID may be different from the routing or swift codes.','Y','Bank ID','Bank ID',TO_TIMESTAMP('2013-12-14 12:21:17','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Dec 14, 2013 12:21:18 PM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=1000312 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Dec 14, 2013 12:22:04 PM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,1001278,1000312,0,10,296,'BankID',TO_TIMESTAMP('2013-12-14 12:22:04','YYYY-MM-DD HH24:MI:SS'),0,'The Bank ID number used in OFX and or EFT transactions.  Used to match import data with the bank.','U',20,'The Bank ID may be different from the routing or swift codes.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Bank ID',0,TO_TIMESTAMP('2013-12-14 12:22:04','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Dec 14, 2013 12:22:04 PM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=1001278 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Dec 14, 2013 12:22:47 PM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
ALTER TABLE C_Bank ADD COLUMN BankID VARCHAR(20) DEFAULT NULL 
;

