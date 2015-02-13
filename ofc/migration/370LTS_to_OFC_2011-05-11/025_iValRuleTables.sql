--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

SET search_path = adempiere, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: ad_impval_rule; Type: TABLE; Schema: adempiere; Owner: adempiere; Tablespace: 
--

CREATE TABLE ad_impval_rule (
    ad_client_id numeric(10,0) NOT NULL,
    ad_impval_rule_id numeric(10,0) NOT NULL,
    ad_impvalidatorrules_id numeric(10,0) DEFAULT NULL::numeric,
    ad_org_id numeric(10,0) NOT NULL,
    ad_table_id numeric(10,0) DEFAULT NULL::numeric,
    created timestamp without time zone NOT NULL,
    createdby numeric(10,0) NOT NULL,
    description character varying(255) DEFAULT NULL::character varying,
    help character varying(2000) DEFAULT NULL::character varying,
    isactive character(1) DEFAULT 'Y'::bpchar NOT NULL,
    name character varying(60) NOT NULL,
    updated timestamp without time zone NOT NULL,
    updatedby numeric(10,0) NOT NULL,
    value character varying(40) NOT NULL,
    validatortiming character varying(10) NOT NULL,
    CONSTRAINT ad_impval_rule_isactive_check CHECK ((isactive = ANY (ARRAY['Y'::bpchar, 'N'::bpchar])))
);


ALTER TABLE adempiere.ad_impval_rule OWNER TO adempiere;

--
-- Name: ad_impvalidatorrules; Type: TABLE; Schema: adempiere; Owner: adempiere; Tablespace: 
--

CREATE TABLE ad_impvalidatorrules (
    ad_client_id numeric(10,0) NOT NULL,
    ad_impvalidatorrules_id numeric(10,0) NOT NULL,
    ad_org_id numeric(10,0) NOT NULL,
    ad_table_id numeric(10,0) NOT NULL,
    created timestamp without time zone NOT NULL,
    createdby numeric(10,0) NOT NULL,
    description character varying(255) DEFAULT NULL::character varying,
    help character varying(2000) DEFAULT NULL::character varying,
    isactive character(1) DEFAULT 'Y'::bpchar NOT NULL,
    name character varying(60) NOT NULL,
    updated timestamp without time zone NOT NULL,
    updatedby numeric(10,0) NOT NULL,
    value character varying(40) NOT NULL,
    CONSTRAINT ad_impvalidatorrules_isactive_check CHECK ((isactive = ANY (ARRAY['Y'::bpchar, 'N'::bpchar])))
);


ALTER TABLE adempiere.ad_impvalidatorrules OWNER TO adempiere;

--
-- Name: ad_impvalrule_action; Type: TABLE; Schema: adempiere; Owner: adempiere; Tablespace: 
--

CREATE TABLE ad_impvalrule_action (
    ad_client_id numeric(10,0) NOT NULL,
    ad_column_id numeric(10,0) DEFAULT NULL::numeric,
    ad_impval_rule_id numeric(10,0) DEFAULT NULL::numeric,
    ad_impvalrule_action_id numeric(10,0) NOT NULL,
    ad_org_id numeric(10,0) NOT NULL,
    ad_table_id numeric(10,0) DEFAULT NULL::numeric,
    created timestamp without time zone NOT NULL,
    createdby numeric(10,0) NOT NULL,
    description character varying(255) DEFAULT NULL::character varying,
    isactive character(1) DEFAULT 'Y'::bpchar NOT NULL,
    seqno numeric,
    updated timestamp without time zone NOT NULL,
    updatedby numeric(10,0) NOT NULL,
    newvalue character varying(2000),
    ad_column_from character varying(10),
    CONSTRAINT ad_impvalrule_action_isactive_check CHECK ((isactive = ANY (ARRAY['Y'::bpchar, 'N'::bpchar])))
);


ALTER TABLE adempiere.ad_impvalrule_action OWNER TO adempiere;

--
-- Name: ad_impvalrule_cond; Type: TABLE; Schema: adempiere; Owner: adempiere; Tablespace: 
--

CREATE TABLE ad_impvalrule_cond (
    ad_client_id numeric(10,0) NOT NULL,
    ad_column_id numeric(10,0) DEFAULT NULL::numeric,
    ad_impval_rule_id numeric(10,0) NOT NULL,
    ad_impvalrule_cond_id numeric(10,0) NOT NULL,
    ad_org_id numeric(10,0) NOT NULL,
    andor character varying(3) DEFAULT NULL::character varying,
    created timestamp without time zone NOT NULL,
    createdby numeric(10,0) NOT NULL,
    help character varying(2000) DEFAULT NULL::character varying,
    isactive character(1) DEFAULT 'Y'::bpchar NOT NULL,
    leftbracket character varying(3) DEFAULT NULL::character varying,
    operator character varying(5) DEFAULT NULL::character varying,
    rightbracket character varying(3) DEFAULT NULL::character varying,
    seqno numeric(10,0) DEFAULT NULL::numeric,
    updated timestamp without time zone NOT NULL,
    updatedby numeric(10,0) NOT NULL,
    value1 character varying(60),
    value2 character varying(60) DEFAULT NULL::character varying,
    CONSTRAINT ad_impvalrule_cond_isactive_check CHECK ((isactive = ANY (ARRAY['Y'::bpchar, 'N'::bpchar])))
);


ALTER TABLE adempiere.ad_impvalrule_cond OWNER TO adempiere;

--
-- Data for Name: ad_impval_rule; Type: TABLE DATA; Schema: adempiere; Owner: adempiere
--

COPY ad_impval_rule (ad_client_id, ad_impval_rule_id, ad_impvalidatorrules_id, ad_org_id, ad_table_id, created, createdby, description, help, isactive, name, updated, updatedby, value, validatortiming) FROM stdin;
1000000	1000001	1000000	1000000	\N	2014-02-13 07:09:03	100	\N	\N	Y	Amex credit card deposits	2014-02-13 12:43:22	100	Amex	10
1000000	1000002	1000000	1000000	\N	2014-02-13 07:45:30	100	Visa, Mastercard and ATM	\N	Y	MRCH Deposits	2014-02-13 12:43:29	100	MRCH Deposits	10
1000000	1000003	1000000	1000000	\N	2014-02-13 07:51:23	100	\N	\N	Y	Bank fees and charges	2014-02-13 12:43:33	100	Bank fees	10
1000000	1000004	1000000	1000000	\N	2014-02-13 08:49:50	100	\N	\N	Y	Cheques	2014-02-13 12:43:38	100	Cheques	10
1000000	1000005	1000000	1000000	\N	2014-02-13 12:11:50	100	\N	\N	Y	Set Statement Date	2014-02-13 12:43:44	100	Statement Date	10
1000000	1000006	1000000	1000000	\N	2014-02-13 12:15:18	100	\N	\N	Y	Set the bank account	2014-02-13 12:43:49	100	Bank Account	10
\.


--
-- Data for Name: ad_impvalidatorrules; Type: TABLE DATA; Schema: adempiere; Owner: adempiere
--

COPY ad_impvalidatorrules (ad_client_id, ad_impvalidatorrules_id, ad_org_id, ad_table_id, created, createdby, description, help, isactive, name, updated, updatedby, value) FROM stdin;
1000000	1000000	1000000	600	2014-01-27 09:50:10	1000893	\N	\N	Y	RBC Import Validator	2014-01-27 09:50:10	1000893	RBC Import
\.


--
-- Data for Name: ad_impvalrule_action; Type: TABLE DATA; Schema: adempiere; Owner: adempiere
--

COPY ad_impvalrule_action (ad_client_id, ad_column_id, ad_impval_rule_id, ad_impvalrule_action_id, ad_org_id, ad_table_id, created, createdby, description, isactive, seqno, updated, updatedby, newvalue, ad_column_from) FROM stdin;
1000000	9309	1000001	1000002	1000000	\N	2014-02-13 07:42:32	100	Set charge to Bank - AMEX Deposit	Y	10	2014-02-13 07:42:32	100	1000165	\N
1000000	9309	1000002	1000003	1000000	\N	2014-02-13 07:48:55	100	Set charge to Bank - MRCH Deposit	Y	10	2014-02-13 07:48:55	100	1000161	\N
1000000	9299	1000002	1000004	1000000	\N	2014-02-13 07:49:36	100	\N	Y	20	2014-02-13 07:49:36	100	Credit card deposit	\N
1000000	9299	1000001	1000005	1000000	\N	2014-02-13 07:50:05	100	\N	Y	20	2014-02-13 07:50:05	100	AMEX Deposit	\N
1000000	9288	1000005	1000007	1000000	\N	2014-02-13 12:13:29	100	\N	Y	10	2014-02-13 12:13:29	100	\N	10373
1000000	9297	1000006	1000008	1000000	\N	2014-02-13 12:16:32	100	RBC	Y	10	2014-02-13 12:16:32	100	1000000	\N
1000000	9309	1000003	1000009	1000000	\N	2014-02-14 06:38:42	100	Bank charges	Y	10	2014-02-14 06:38:42	100	1000057	\N
1000000	9305	1000001	1000010	1000000	\N	2014-02-14 06:46:41	100	Copy the statement amount to the charge	Y	30	2014-02-14 06:46:41	100	\N	9318
1000000	9322	1000001	1000011	1000000	\N	2014-02-14 06:47:09	100	Set transaction amount to zero	Y	40	2014-02-14 06:47:09	100	0	\N
1000000	9305	1000002	1000012	1000000	\N	2014-02-14 06:47:51	100	\N	Y	30	2014-02-14 06:47:51	100	\N	9318
1000000	9322	1000002	1000013	1000000	\N	2014-02-14 06:48:04	100	\N	Y	40	2014-02-14 06:48:04	100	0	\N
1000000	9322	1000003	1000015	1000000	\N	2014-02-14 06:52:33	100	\N	Y	30	2014-02-14 08:30:05	100	0	\N
1000000	9305	1000003	1000016	1000000	\N	2014-02-14 08:30:55	100	\N	Y	20	2014-02-14 08:32:37	100	\N	9318
1000000	10381	1000004	1000006	1000000	\N	2014-02-13 12:10:16	100	\N	Y	10	2014-02-14 09:57:29	100	@SQL=LTRIM(EFTCheckNo, '0')	\N
\.


--
-- Data for Name: ad_impvalrule_cond; Type: TABLE DATA; Schema: adempiere; Owner: adempiere
--

COPY ad_impvalrule_cond (ad_client_id, ad_column_id, ad_impval_rule_id, ad_impvalrule_cond_id, ad_org_id, andor, created, createdby, help, isactive, leftbracket, operator, rightbracket, seqno, updated, updatedby, value1, value2) FROM stdin;
1000000	10016	1000001	1000001	1000000	\N	2014-02-13 07:33:15	100	\N	Y	\N	=	\N	10	2014-02-13 07:33:15	100	AMEX 9304985493 	\N
1000000	10018	1000001	1000003	1000000	A	2014-02-13 07:35:43	100	\N	Y	\N	=	\N	30	2014-02-13 07:35:43	100	CREDIT	\N
1000000	10019	1000001	1000002	1000000	A	2014-02-13 07:35:12	100	\N	Y	\N	=	\N	20	2014-02-13 07:35:50	100	MISC PAYMENT	\N
1000000	10016	1000002	1000004	1000000	\N	2014-02-13 07:46:25	100	\N	Y	\N	=	\N	10	2014-02-13 07:46:25	100	MRCH25756200010 	\N
1000000	10018	1000003	1000008	1000000	\N	2014-02-13 07:52:22	100	\N	Y	\N	=	\N	5	2014-02-13 07:52:22	100	DEBIT	\N
1000000	10019	1000003	1000009	1000000	O	2014-02-13 07:53:29	100	\N	Y	\N	=	\N	20	2014-02-13 07:53:44	100	SERVICE FEES	\N
1000000	10019	1000003	1000010	1000000	O	2014-02-13 07:55:09	100	\N	Y	\N	=	\N	30	2014-02-13 07:55:09	100	SERVICE FEE	\N
1000000	10019	1000003	1000011	1000000	O	2014-02-13 07:55:26	100	\N	Y	\N	=	\N	40	2014-02-13 07:55:26	100	ELECTRONIC ITEM FEE	\N
1000000	10019	1000003	1000012	1000000	O	2014-02-13 07:55:56	100	\N	Y	\N	=	\N	50	2014-02-13 07:55:56	100	ITEMS ON DEP. FEE	\N
1000000	10019	1000003	1000013	1000000	O	2014-02-13 07:56:21	100	\N	Y	\N	=	\N	60	2014-02-13 07:56:21	100	IN BRANCH CD FEE	\N
1000000	10019	1000003	1000014	1000000	O	2014-02-13 07:56:57	100	\N	Y	\N	=	\N	70	2014-02-13 08:09:05	100	FUNDS TRANSFER FEE	\N
1000000	10019	1000003	1000015	1000000	O	2014-02-13 08:09:29	100	\N	Y	\N	=	)	80	2014-02-13 08:40:51	100	EQUIPMENT RENT	\N
1000000	10018	1000004	1000016	1000000	\N	2014-02-13 08:50:37	100	\N	Y	\N	=	\N	10	2014-02-13 08:50:37	100	CHECK	\N
1000000	9289	1000006	1000019	1000000	\N	2014-02-13 12:15:49	100	\N	Y	\N	=	\N	10	2014-02-13 12:15:49	100	007761151851	\N
1000000	10018	1000002	1000005	1000000	A	2014-02-13 07:46:44	100	\N	Y	\N	=	\N	20	2014-02-13 23:35:58	100	CREDIT	\N
1000000	10019	1000002	1000006	1000000	A	2014-02-13 07:47:08	100	\N	Y	\N	=	\N	30	2014-02-13 23:36:02	100	MISC PAYMENT	\N
1000000	10019	1000004	1000017	1000000	A	2014-02-13 08:51:31	100	\N	Y	\N	~	\N	20	2014-02-13 23:36:52	100	CHEQUE%	\N
1000000	10373	1000005	1000018	1000000	\N	2014-02-13 12:13:02	100	\N	Y	\N	!=	\N	10	2014-02-13 23:37:26	100	\N	\N
1000000	10019	1000003	1000007	1000000	A	2014-02-13 07:51:56	100	\N	Y	(	=	\N	10	2014-02-14 06:28:16	100	MIN MDR FEE	\N
1000000	10019	1000003	1000020	1000000	O	2014-02-14 10:03:22	100	\N	Y	\N	=	\N	35	2014-02-14 10:03:26	100	ACTIVITY FEE	\N
\.


--
-- Name: ad_impval_rule_key; Type: CONSTRAINT; Schema: adempiere; Owner: adempiere; Tablespace: 
--

ALTER TABLE ONLY ad_impval_rule
    ADD CONSTRAINT ad_impval_rule_key PRIMARY KEY (ad_impval_rule_id);


--
-- Name: ad_impvalidatorrules_key; Type: CONSTRAINT; Schema: adempiere; Owner: adempiere; Tablespace: 
--

ALTER TABLE ONLY ad_impvalidatorrules
    ADD CONSTRAINT ad_impvalidatorrules_key PRIMARY KEY (ad_impvalidatorrules_id);


--
-- Name: ad_impvalrule_action_key; Type: CONSTRAINT; Schema: adempiere; Owner: adempiere; Tablespace: 
--

ALTER TABLE ONLY ad_impvalrule_action
    ADD CONSTRAINT ad_impvalrule_action_key PRIMARY KEY (ad_impvalrule_action_id);


--
-- Name: ad_impvalrule_cond_key; Type: CONSTRAINT; Schema: adempiere; Owner: adempiere; Tablespace: 
--

ALTER TABLE ONLY ad_impvalrule_cond
    ADD CONSTRAINT ad_impvalrule_cond_key PRIMARY KEY (ad_impvalrule_cond_id);


--
-- PostgreSQL database dump complete
--

