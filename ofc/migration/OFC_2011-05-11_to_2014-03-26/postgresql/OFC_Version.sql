-- Jun 14, 2010 4:46:33 PM COT
-- Preparing release of OFC Custom
UPDATE AD_Column SET FieldLength=10,Updated=TO_TIMESTAMP('2014-03-26 00:00:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=9328
;

UPDATE AD_SYSTEM
 SET releaseno = 'OFC Custom', VERSION = '2014-03-26' 
  WHERE ad_system_id = 0 AND ad_client_id = 0;
