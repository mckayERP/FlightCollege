-- Feb 20, 2014 7:44:21 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,1001278,1384,0,19,1000063,'C_Payment_ID',TO_TIMESTAMP('2014-02-20 07:44:20','YYYY-MM-DD HH24:MI:SS'),0,'Payment identifier','U',22,'The Payment is a unique identifier of this payment.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Payment',0,TO_TIMESTAMP('2014-02-20 07:44:20','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 20, 2014 7:44:27 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,1001279,1384,0,19,1000063,'C_Payment_ID',TO_TIMESTAMP('2014-02-20 07:44:27','YYYY-MM-DD HH24:MI:SS'),0,'Payment identifier','U',22,'The Payment is a unique identifier of this payment.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Payment',0,TO_TIMESTAMP('2014-02-20 07:44:27','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 20, 2014 7:44:31 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,1001280,1384,0,19,1000063,'C_Payment_ID',TO_TIMESTAMP('2014-02-20 07:44:31','YYYY-MM-DD HH24:MI:SS'),0,'Payment identifier','U',22,'The Payment is a unique identifier of this payment.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Payment',0,TO_TIMESTAMP('2014-02-20 07:44:31','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 20, 2014 7:44:36 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,1001281,1384,0,19,1000063,'C_Payment_ID',TO_TIMESTAMP('2014-02-20 07:44:36','YYYY-MM-DD HH24:MI:SS'),0,'Payment identifier','U',22,'The Payment is a unique identifier of this payment.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Payment',0,TO_TIMESTAMP('2014-02-20 07:44:36','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 20, 2014 7:44:51 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,1001282,1384,0,19,1000063,'C_Payment_ID',TO_TIMESTAMP('2014-02-20 07:44:51','YYYY-MM-DD HH24:MI:SS'),0,'Payment identifier','U',22,'The Payment is a unique identifier of this payment.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Payment',0,TO_TIMESTAMP('2014-02-20 07:44:51','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 20, 2014 7:46:32 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Sequence SET Name = (SELECT TableName FROM AD_Table t WHERE t.IsView='N' AND UPPER(AD_Sequence.Name)=UPPER(t.TableName)) WHERE AD_Sequence.IsTableID='Y' AND EXISTS (SELECT * FROM AD_Table t WHERE t.IsActive='Y' AND t.IsView='N' AND UPPER(AD_Sequence.Name)=UPPER(t.TableName) AND AD_Sequence.Name<>t.TableName)
;

-- Feb 20, 2014 7:46:33 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Sequence SET CurrentNext=1001428,Updated=TO_TIMESTAMP('2014-02-20 07:46:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Sequence_ID=3
;

-- Feb 20, 2014 7:46:33 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Sequence SET CurrentNext=1000329,Updated=TO_TIMESTAMP('2014-02-20 07:46:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Sequence_ID=188
;

-- Feb 20, 2014 7:46:33 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Sequence SET CurrentNext=1001202,Updated=TO_TIMESTAMP('2014-02-20 07:46:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Sequence_ID=4
;

-- Feb 20, 2014 7:46:33 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Sequence SET CurrentNextSys=53017,Updated=TO_TIMESTAMP('2014-02-20 07:46:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Sequence_ID=313
;

-- Feb 20, 2014 7:46:34 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Sequence SET CurrentNext=1245867,Updated=TO_TIMESTAMP('2014-02-20 07:46:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Sequence_ID=1191
;

-- Feb 20, 2014 7:46:34 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Sequence SET CurrentNext=1000070,Updated=TO_TIMESTAMP('2014-02-20 07:46:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Sequence_ID=7
;

-- Feb 20, 2014 7:46:34 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Sequence SET CurrentNext=1000011,Updated=TO_TIMESTAMP('2014-02-20 07:46:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Sequence_ID=9
;

-- Feb 20, 2014 7:46:34 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Sequence SET CurrentNext=1000001,Updated=TO_TIMESTAMP('2014-02-20 07:46:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Sequence_ID=53012
;

-- Feb 20, 2014 7:46:35 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Sequence SET CurrentNextSys=50052,Updated=TO_TIMESTAMP('2014-02-20 07:46:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Sequence_ID=579
;

-- Feb 20, 2014 7:46:35 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Sequence SET CurrentNextSys=51551,Updated=TO_TIMESTAMP('2014-02-20 07:46:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Sequence_ID=575
;

-- Feb 20, 2014 7:46:35 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Sequence SET CurrentNext=1000039,Updated=TO_TIMESTAMP('2014-02-20 07:46:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Sequence_ID=199
;

-- Feb 20, 2014 7:46:35 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Sequence SET CurrentNext=1000310,Updated=TO_TIMESTAMP('2014-02-20 07:46:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Sequence_ID=200
;

-- Feb 20, 2014 7:46:35 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Sequence SET CurrentNext=1000163, CurrentNextSys=53628,Updated=TO_TIMESTAMP('2014-02-20 07:46:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Sequence_ID=11
;

-- Feb 20, 2014 7:46:35 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Sequence SET CurrentNextSys=53373,Updated=TO_TIMESTAMP('2014-02-20 07:46:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Sequence_ID=14
;

-- Feb 20, 2014 7:46:35 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Sequence SET CurrentNextSys=53033,Updated=TO_TIMESTAMP('2014-02-20 07:46:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Sequence_ID=298
;

-- Feb 20, 2014 7:46:35 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Sequence SET CurrentNextSys=50005,Updated=TO_TIMESTAMP('2014-02-20 07:46:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Sequence_ID=53075
;

-- Feb 20, 2014 7:46:35 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Sequence SET CurrentNext=1000275, CurrentNextSys=53455,Updated=TO_TIMESTAMP('2014-02-20 07:46:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Sequence_ID=16
;

-- Feb 20, 2014 7:46:35 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Sequence SET CurrentNext=1000007, CurrentNextSys=50059,Updated=TO_TIMESTAMP('2014-02-20 07:46:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Sequence_ID=50009
;

-- Feb 20, 2014 7:46:35 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Sequence SET CurrentNext=1000060,Updated=TO_TIMESTAMP('2014-02-20 07:46:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Sequence_ID=19
;

-- Feb 20, 2014 7:46:35 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Sequence SET CurrentNext=1000065,Updated=TO_TIMESTAMP('2014-02-20 07:46:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Sequence_ID=21
;

-- Feb 20, 2014 7:46:36 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Sequence SET CurrentNext=1000007,Updated=TO_TIMESTAMP('2014-02-20 07:46:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Sequence_ID=26
;

-- Feb 20, 2014 7:46:36 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Sequence SET CurrentNext=1000004,Updated=TO_TIMESTAMP('2014-02-20 07:46:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Sequence_ID=32
;

-- Feb 20, 2014 7:46:36 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Sequence SET CurrentNext=1000003,Updated=TO_TIMESTAMP('2014-02-20 07:46:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Sequence_ID=33
;

-- Feb 20, 2014 7:46:36 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Sequence SET CurrentNext=1000024,Updated=TO_TIMESTAMP('2014-02-20 07:46:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Sequence_ID=27
;

-- Feb 20, 2014 7:46:36 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Sequence SET CurrentNext=1000001,Updated=TO_TIMESTAMP('2014-02-20 07:46:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Sequence_ID=29
;

-- Feb 20, 2014 7:46:38 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Sequence SET CurrentNext=1000044,Updated=TO_TIMESTAMP('2014-02-20 07:46:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Sequence_ID=133
;

-- Feb 20, 2014 7:46:49 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Sequence SET CurrentNext=1000012,Updated=TO_TIMESTAMP('2014-02-20 07:46:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Sequence_ID=1000238
;

-- Feb 20, 2014 7:46:52 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (11,0,1000275,TO_TIMESTAMP('2014-02-20 07:46:52','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'DocumentNo/Value for Table OFC_Booking_Audit_Trail',1,'Y','N','Y','N','DocumentNo_OFC_Booking_Audit_Trail','N',1000000,TO_TIMESTAMP('2014-02-20 07:46:52','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 20, 2014 7:46:52 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (11,0,1000276,TO_TIMESTAMP('2014-02-20 07:46:52','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'DocumentNo/Value for Table I_OFC_Flightsheet',1,'Y','N','Y','N','DocumentNo_I_OFC_Flightsheet','N',1000000,TO_TIMESTAMP('2014-02-20 07:46:52','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 20, 2014 7:46:52 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (11,0,1000277,TO_TIMESTAMP('2014-02-20 07:46:52','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'DocumentNo/Value for Table OFC_Endorsement',1,'Y','N','Y','N','DocumentNo_OFC_Endorsement','N',1000000,TO_TIMESTAMP('2014-02-20 07:46:52','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 20, 2014 7:46:52 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (11,0,1000278,TO_TIMESTAMP('2014-02-20 07:46:52','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'DocumentNo/Value for Table OFC_Pilot_Quals',1,'Y','N','Y','N','DocumentNo_OFC_Pilot_Quals','N',1000000,TO_TIMESTAMP('2014-02-20 07:46:52','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 20, 2014 7:46:52 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (11,0,1000279,TO_TIMESTAMP('2014-02-20 07:46:52','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'DocumentNo/Value for Table AD_ImpValidatorRules',1,'Y','N','Y','N','DocumentNo_AD_ImpValidatorRules','N',1000000,TO_TIMESTAMP('2014-02-20 07:46:52','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 20, 2014 7:46:52 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (11,0,1000280,TO_TIMESTAMP('2014-02-20 07:46:52','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'DocumentNo/Value for Table OFC_Advanced_Inst',1,'Y','N','Y','N','DocumentNo_OFC_Advanced_Inst','N',1000000,TO_TIMESTAMP('2014-02-20 07:46:52','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 20, 2014 7:46:52 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (11,0,1000281,TO_TIMESTAMP('2014-02-20 07:46:52','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'DocumentNo/Value for Table OFC_Syllabus_Details',1,'Y','N','Y','N','DocumentNo_OFC_Syllabus_Details','N',1000000,TO_TIMESTAMP('2014-02-20 07:46:52','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 20, 2014 7:46:52 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (11,0,1000282,TO_TIMESTAMP('2014-02-20 07:46:52','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'DocumentNo/Value for Table OFC_Aircraft',1,'Y','N','Y','N','DocumentNo_OFC_Aircraft','N',1000000,TO_TIMESTAMP('2014-02-20 07:46:52','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 20, 2014 7:46:52 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (11,0,1000283,TO_TIMESTAMP('2014-02-20 07:46:52','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'DocumentNo/Value for Table OFC_Pilot_Medical',1,'Y','N','Y','N','DocumentNo_OFC_Pilot_Medical','N',1000000,TO_TIMESTAMP('2014-02-20 07:46:52','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 20, 2014 7:46:52 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (11,0,1000284,TO_TIMESTAMP('2014-02-20 07:46:52','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'DocumentNo/Value for Table OFC_Training',1,'Y','N','Y','N','DocumentNo_OFC_Training','N',1000000,TO_TIMESTAMP('2014-02-20 07:46:52','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 20, 2014 7:46:52 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (11,0,1000285,TO_TIMESTAMP('2014-02-20 07:46:52','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'DocumentNo/Value for Table OFC_Flightsheet',1,'Y','N','Y','N','DocumentNo_OFC_Flightsheet','N',1000000,TO_TIMESTAMP('2014-02-20 07:46:52','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 20, 2014 7:46:52 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (11,0,1000286,TO_TIMESTAMP('2014-02-20 07:46:52','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'DocumentNo/Value for Table AD_ImpVal_Rule',1,'Y','N','Y','N','DocumentNo_AD_ImpVal_Rule','N',1000000,TO_TIMESTAMP('2014-02-20 07:46:52','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 20, 2014 7:46:52 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,1000287,TO_TIMESTAMP('2014-02-20 07:46:52','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'DocumentNo/Value for Table OFC_Booking_Audit_Trail',1,'Y','N','Y','N','DocumentNo_OFC_Booking_Audit_Trail','N',1000000,TO_TIMESTAMP('2014-02-20 07:46:52','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 20, 2014 7:46:52 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,1000288,TO_TIMESTAMP('2014-02-20 07:46:52','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'DocumentNo/Value for Table I_OFC_Flightsheet',1,'Y','N','Y','N','DocumentNo_I_OFC_Flightsheet','N',1000000,TO_TIMESTAMP('2014-02-20 07:46:52','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 20, 2014 7:46:53 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,1000289,TO_TIMESTAMP('2014-02-20 07:46:53','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'DocumentNo/Value for Table OFC_Endorsement',1,'Y','N','Y','N','DocumentNo_OFC_Endorsement','N',1000000,TO_TIMESTAMP('2014-02-20 07:46:53','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 20, 2014 7:46:53 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,1000290,TO_TIMESTAMP('2014-02-20 07:46:53','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'DocumentNo/Value for Table OFC_Pilot_Quals',1,'Y','N','Y','N','DocumentNo_OFC_Pilot_Quals','N',1000000,TO_TIMESTAMP('2014-02-20 07:46:53','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 20, 2014 7:46:53 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,1000291,TO_TIMESTAMP('2014-02-20 07:46:53','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'DocumentNo/Value for Table AD_ImpValidatorRules',1,'Y','N','Y','N','DocumentNo_AD_ImpValidatorRules','N',1000000,TO_TIMESTAMP('2014-02-20 07:46:53','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 20, 2014 7:46:53 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,1000292,TO_TIMESTAMP('2014-02-20 07:46:53','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'DocumentNo/Value for Table OFC_Advanced_Inst',1,'Y','N','Y','N','DocumentNo_OFC_Advanced_Inst','N',1000000,TO_TIMESTAMP('2014-02-20 07:46:53','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 20, 2014 7:46:53 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,1000293,TO_TIMESTAMP('2014-02-20 07:46:53','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'DocumentNo/Value for Table OFC_Syllabus_Details',1,'Y','N','Y','N','DocumentNo_OFC_Syllabus_Details','N',1000000,TO_TIMESTAMP('2014-02-20 07:46:53','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 20, 2014 7:46:53 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,1000294,TO_TIMESTAMP('2014-02-20 07:46:53','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'DocumentNo/Value for Table OFC_Aircraft',1,'Y','N','Y','N','DocumentNo_OFC_Aircraft','N',1000000,TO_TIMESTAMP('2014-02-20 07:46:53','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 20, 2014 7:46:53 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,1000295,TO_TIMESTAMP('2014-02-20 07:46:53','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'DocumentNo/Value for Table OFC_Pilot_Medical',1,'Y','N','Y','N','DocumentNo_OFC_Pilot_Medical','N',1000000,TO_TIMESTAMP('2014-02-20 07:46:53','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 20, 2014 7:46:53 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,1000296,TO_TIMESTAMP('2014-02-20 07:46:53','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'DocumentNo/Value for Table OFC_Training',1,'Y','N','Y','N','DocumentNo_OFC_Training','N',1000000,TO_TIMESTAMP('2014-02-20 07:46:53','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 20, 2014 7:46:53 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,1000297,TO_TIMESTAMP('2014-02-20 07:46:53','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'DocumentNo/Value for Table OFC_Flightsheet',1,'Y','N','Y','N','DocumentNo_OFC_Flightsheet','N',1000000,TO_TIMESTAMP('2014-02-20 07:46:53','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 20, 2014 7:46:53 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,1000298,TO_TIMESTAMP('2014-02-20 07:46:53','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'DocumentNo/Value for Table AD_ImpVal_Rule',1,'Y','N','Y','N','DocumentNo_AD_ImpVal_Rule','N',1000000,TO_TIMESTAMP('2014-02-20 07:46:53','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 20, 2014 7:46:53 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (1000000,0,1000299,TO_TIMESTAMP('2014-02-20 07:46:53','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'DocumentNo/Value for Table OFC_Booking_Audit_Trail',1,'Y','N','Y','N','DocumentNo_OFC_Booking_Audit_Trail','N',1000000,TO_TIMESTAMP('2014-02-20 07:46:53','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 20, 2014 7:46:53 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (1000000,0,1000300,TO_TIMESTAMP('2014-02-20 07:46:53','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'DocumentNo/Value for Table I_OFC_Flightsheet',1,'Y','N','Y','N','DocumentNo_I_OFC_Flightsheet','N',1000000,TO_TIMESTAMP('2014-02-20 07:46:53','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 20, 2014 7:46:53 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (1000000,0,1000301,TO_TIMESTAMP('2014-02-20 07:46:53','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'DocumentNo/Value for Table OFC_Endorsement',1,'Y','N','Y','N','DocumentNo_OFC_Endorsement','N',1000000,TO_TIMESTAMP('2014-02-20 07:46:53','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 20, 2014 7:46:53 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (1000000,0,1000302,TO_TIMESTAMP('2014-02-20 07:46:53','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'DocumentNo/Value for Table AD_ImpValidatorRules',1,'Y','N','Y','N','DocumentNo_AD_ImpValidatorRules','N',1000000,TO_TIMESTAMP('2014-02-20 07:46:53','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 20, 2014 7:46:53 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (1000000,0,1000303,TO_TIMESTAMP('2014-02-20 07:46:53','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'DocumentNo/Value for Table C_OrderSource',1,'Y','N','Y','N','DocumentNo_C_OrderSource','N',1000000,TO_TIMESTAMP('2014-02-20 07:46:53','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 20, 2014 7:46:53 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (1000000,0,1000304,TO_TIMESTAMP('2014-02-20 07:46:53','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'DocumentNo/Value for Table OFC_Syllabus_Details',1,'Y','N','Y','N','DocumentNo_OFC_Syllabus_Details','N',1000000,TO_TIMESTAMP('2014-02-20 07:46:53','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 20, 2014 7:46:53 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (1000000,0,1000305,TO_TIMESTAMP('2014-02-20 07:46:53','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'DocumentNo/Value for Table OFC_Training',1,'Y','N','Y','N','DocumentNo_OFC_Training','N',1000000,TO_TIMESTAMP('2014-02-20 07:46:53','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 20, 2014 7:46:53 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (1000000,0,1000306,TO_TIMESTAMP('2014-02-20 07:46:53','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'DocumentNo/Value for Table I_Movement',1,'Y','N','Y','N','DocumentNo_I_Movement','N',1000000,TO_TIMESTAMP('2014-02-20 07:46:53','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 20, 2014 7:46:53 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (1000000,0,1000307,TO_TIMESTAMP('2014-02-20 07:46:53','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'DocumentNo/Value for Table OFC_Flightsheet',1,'Y','N','Y','N','DocumentNo_OFC_Flightsheet','N',1000000,TO_TIMESTAMP('2014-02-20 07:46:53','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 20, 2014 7:46:53 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (1000000,0,1000308,TO_TIMESTAMP('2014-02-20 07:46:53','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'DocumentNo/Value for Table AD_ImpVal_Rule',1,'Y','N','Y','N','DocumentNo_AD_ImpVal_Rule','N',1000000,TO_TIMESTAMP('2014-02-20 07:46:53','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 20, 2014 7:47:20 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,1001428,1384,0,19,1000063,'C_Payment_ID',TO_TIMESTAMP('2014-02-20 07:47:20','YYYY-MM-DD HH24:MI:SS'),0,'Payment identifier','U',22,'The Payment is a unique identifier of this payment.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Payment',0,TO_TIMESTAMP('2014-02-20 07:47:20','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 20, 2014 7:47:20 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=1001428 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 20, 2014 7:47:29 AM EST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
ALTER TABLE C_BankDeposit ADD COLUMN C_Payment_ID NUMERIC(10) DEFAULT NULL 
;

