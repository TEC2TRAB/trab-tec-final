--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.7
-- Dumped by pg_dump version 9.3.7
-- Started on 2016-01-24 21:30:53

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 179 (class 3079 OID 11750)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 1995 (class 0 OID 0)
-- Dependencies: 179
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 170 (class 1259 OID 41629)
-- Name: cliente; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE cliente (
    id_cliente integer NOT NULL,
    cpf character varying(11),
    data_cadastro date
);


ALTER TABLE public.cliente OWNER TO postgres;

--
-- TOC entry 171 (class 1259 OID 41632)
-- Name: cliente_id_cliente_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE cliente_id_cliente_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cliente_id_cliente_seq OWNER TO postgres;

--
-- TOC entry 1996 (class 0 OID 0)
-- Dependencies: 171
-- Name: cliente_id_cliente_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE cliente_id_cliente_seq OWNED BY cliente.id_cliente;


--
-- TOC entry 172 (class 1259 OID 41634)
-- Name: funcionario; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE funcionario (
    id_funcionario integer NOT NULL,
    cpf character varying(11),
    admissao date,
    demissao date,
    login character varying(25),
    senha character varying(40),
    permissao character varying(15)
);


ALTER TABLE public.funcionario OWNER TO postgres;

--
-- TOC entry 173 (class 1259 OID 41637)
-- Name: funcionario_id_funcionario_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE funcionario_id_funcionario_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.funcionario_id_funcionario_seq OWNER TO postgres;

--
-- TOC entry 1997 (class 0 OID 0)
-- Dependencies: 173
-- Name: funcionario_id_funcionario_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE funcionario_id_funcionario_seq OWNED BY funcionario.id_funcionario;


--
-- TOC entry 174 (class 1259 OID 41639)
-- Name: itens; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE itens (
    id_venda integer,
    id_produto integer,
    quantidade real,
    preco real
);


ALTER TABLE public.itens OWNER TO postgres;

--
-- TOC entry 175 (class 1259 OID 41642)
-- Name: pessoa; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE pessoa (
    numero integer,
    data_nascimento date,
    sexo character(1),
    nome character varying(50),
    cep character varying(10),
    bairro character varying(30),
    cidade character varying(30),
    estado character varying(30),
    complemento character varying(30),
    cpf character varying(11) NOT NULL,
    rg character varying(15),
    rua character varying(50)
);


ALTER TABLE public.pessoa OWNER TO postgres;

--
-- TOC entry 176 (class 1259 OID 41645)
-- Name: produto; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE produto (
    id_produto integer NOT NULL,
    nome character varying(30),
    preco real NOT NULL,
    quantidade real NOT NULL,
    descricao character(100)
);


ALTER TABLE public.produto OWNER TO postgres;

--
-- TOC entry 177 (class 1259 OID 41648)
-- Name: venda; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE venda (
    id_venda integer NOT NULL,
    id_vendedor integer,
    cpf_cliente character varying(11),
    valor_total real,
    data_venda date,
    hora character varying(8)
);


ALTER TABLE public.venda OWNER TO postgres;

--
-- TOC entry 178 (class 1259 OID 41651)
-- Name: venda_id_venda_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE venda_id_venda_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.venda_id_venda_seq OWNER TO postgres;

--
-- TOC entry 1998 (class 0 OID 0)
-- Dependencies: 178
-- Name: venda_id_venda_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE venda_id_venda_seq OWNED BY venda.id_venda;


--
-- TOC entry 1847 (class 2604 OID 41653)
-- Name: id_cliente; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cliente ALTER COLUMN id_cliente SET DEFAULT nextval('cliente_id_cliente_seq'::regclass);


--
-- TOC entry 1848 (class 2604 OID 41654)
-- Name: id_funcionario; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY funcionario ALTER COLUMN id_funcionario SET DEFAULT nextval('funcionario_id_funcionario_seq'::regclass);


--
-- TOC entry 1849 (class 2604 OID 41655)
-- Name: id_venda; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY venda ALTER COLUMN id_venda SET DEFAULT nextval('venda_id_venda_seq'::regclass);


--
-- TOC entry 1979 (class 0 OID 41629)
-- Dependencies: 170
-- Data for Name: cliente; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY cliente (id_cliente, cpf, data_cadastro) FROM stdin;
2	51073628779	2015-06-25
3	6001137366	2015-06-25
6	60456164308	2015-06-30
\.


--
-- TOC entry 1999 (class 0 OID 0)
-- Dependencies: 171
-- Name: cliente_id_cliente_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('cliente_id_cliente_seq', 6, true);


--
-- TOC entry 1981 (class 0 OID 41634)
-- Dependencies: 172
-- Data for Name: funcionario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY funcionario (id_funcionario, cpf, admissao, demissao, login, senha, permissao) FROM stdin;
3	6300273318	2015-06-20	\N	admin	21232f297a57a5a743894a0e4a801fc3	Administrador
4	48174134840	2015-06-25	\N	ant	63b07e828bf016e976ff95d6ee07a105	Vendedor
6	4862121306	2015-06-30	\N	admin1	21232f297a57a5a743894a0e4a801fc3	Administrador
7	60456164308	2015-06-30	\N	admin2	21232f297a57a5a743894a0e4a801fc3	Administrador
8	2970276380	2015-06-30	2015-06-30	adm	760061f6bfde75c29af12f252d4d3345	Vendedor
\.


--
-- TOC entry 2000 (class 0 OID 0)
-- Dependencies: 173
-- Name: funcionario_id_funcionario_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('funcionario_id_funcionario_seq', 8, true);


--
-- TOC entry 1983 (class 0 OID 41639)
-- Dependencies: 174
-- Data for Name: itens; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY itens (id_venda, id_produto, quantidade, preco) FROM stdin;
17	1	2	3.21000004
17	1	2	3.21000004
18	1	1	3.21000004
18	1	1	3.21000004
19	1	1	3.21000004
19	2	2	4
20	1	2	3.21000004
20	2	1	4
21	1	1	3.21000004
21	2	1	4
22	1	1	3.21000004
22	2	2	4
22	2	2	4
23	1	7	3.21000004
23	2	3	4
24	5	2	1.69000006
26	1	2	3.21000004
\.


--
-- TOC entry 1984 (class 0 OID 41642)
-- Dependencies: 175
-- Data for Name: pessoa; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY pessoa (numero, data_nascimento, sexo, nome, cep, bairro, cidade, estado, complemento, cpf, rg, rua) FROM stdin;
234	2015-02-07	M	Admin	60762080	Qualquer	Fortaleza	Ceará	Qualquer	6300273318	20077644551	Qualquer
232	1990-09-05	M	JOSÉ	60605-000	Ali	Fortaleza	Ceará	Aknas	51073628779	2004752261	Adel
123	1989-04-19	M	ANTÔNIO	60660-060	Akjasdn	Fortaleza	Ceará	Alunds	48174134840	2008445056	Alinnk
450	1994-12-12	M	CÉSAR 	61621-015	Jardim Icaraí	Caucaia	Ceará		6001137366	2005010132882	Juaci Sampaio Pontes
321312	2314-07-12	M	RICARDO	32423-423	sdfsdf	ewfwef	Ceará	sdfewf	4862121306	234234	fsdfdsf
22423	2314-07-12	M	RX	23432-423	efsdfsdf	ergdgdfg	Ceará	sdfsdf	60456164308	23324324	dgdfgdf
123	1990-05-19	M	ROBERTO	60898-102	Akas	Fortaleza	Ceará	Laka	2970276380	505649812	Qulq
\.


--
-- TOC entry 1985 (class 0 OID 41645)
-- Dependencies: 176
-- Data for Name: produto; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY produto (id_produto, nome, preco, quantidade, descricao) FROM stdin;
3	MACARRÃO	3.4000001	50	Fortaleza                                                                                           
2	FEIJÃO	4	42	Carioca                                                                                             
5	FAROFA	1.69000006	78	Farinha de Milho                                                                                    
1	ARROZ	3.21000004	0	Integral                                                                                            
9	ARROZ INTEGRAL	2.29999995	40	Pai João                                                                                            
\.


--
-- TOC entry 1986 (class 0 OID 41648)
-- Dependencies: 177
-- Data for Name: venda; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY venda (id_venda, id_vendedor, cpf_cliente, valor_total, data_venda, hora) FROM stdin;
17	3	\N	12.8400002	2015-06-30	08:50:04
18	3	\N	6.42000008	2015-06-30	08:50:18
19	4	\N	11.21	2015-06-30	08:58:37
20	4	\N	10.4200001	2015-06-30	08:59:16
21	3	\N	7.21000004	2015-06-30	09:22:45
22	4	\N	19.2099991	2015-06-30	09:23:38
23	3	\N	34.4700012	2015-06-30	10:23:02
24	4	\N	3.38000011	2015-06-30	10:26:10
26	3	60456164308	6.42000008	2015-06-30	10:30:48
\.


--
-- TOC entry 2001 (class 0 OID 0)
-- Dependencies: 178
-- Name: venda_id_venda_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('venda_id_venda_seq', 26, true);


--
-- TOC entry 1851 (class 2606 OID 41657)
-- Name: cliente_cpf_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_cpf_key UNIQUE (cpf);


--
-- TOC entry 1855 (class 2606 OID 41659)
-- Name: funcionario_cpf_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY funcionario
    ADD CONSTRAINT funcionario_cpf_key UNIQUE (cpf);


--
-- TOC entry 1857 (class 2606 OID 41661)
-- Name: funcionario_login_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY funcionario
    ADD CONSTRAINT funcionario_login_key UNIQUE (login);


--
-- TOC entry 1853 (class 2606 OID 41663)
-- Name: pk_cliente; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY cliente
    ADD CONSTRAINT pk_cliente PRIMARY KEY (id_cliente);


--
-- TOC entry 1859 (class 2606 OID 41665)
-- Name: pk_funcionario; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY funcionario
    ADD CONSTRAINT pk_funcionario PRIMARY KEY (id_funcionario);


--
-- TOC entry 1861 (class 2606 OID 41667)
-- Name: pk_pessoa; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY pessoa
    ADD CONSTRAINT pk_pessoa PRIMARY KEY (cpf);


--
-- TOC entry 1863 (class 2606 OID 41669)
-- Name: pk_produto; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY produto
    ADD CONSTRAINT pk_produto PRIMARY KEY (id_produto);


--
-- TOC entry 1865 (class 2606 OID 41671)
-- Name: pk_venda; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY venda
    ADD CONSTRAINT pk_venda PRIMARY KEY (id_venda);


--
-- TOC entry 1866 (class 2606 OID 41672)
-- Name: fk_cliente; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY cliente
    ADD CONSTRAINT fk_cliente FOREIGN KEY (cpf) REFERENCES pessoa(cpf) ON DELETE CASCADE;


--
-- TOC entry 1867 (class 2606 OID 41677)
-- Name: fk_funcionario; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY funcionario
    ADD CONSTRAINT fk_funcionario FOREIGN KEY (cpf) REFERENCES pessoa(cpf) ON DELETE CASCADE;


--
-- TOC entry 1869 (class 2606 OID 41682)
-- Name: fk_historico_produto; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY itens
    ADD CONSTRAINT fk_historico_produto FOREIGN KEY (id_produto) REFERENCES produto(id_produto);


--
-- TOC entry 1868 (class 2606 OID 41687)
-- Name: fk_historico_venda; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY itens
    ADD CONSTRAINT fk_historico_venda FOREIGN KEY (id_venda) REFERENCES venda(id_venda);


--
-- TOC entry 1871 (class 2606 OID 41692)
-- Name: fk_venda_cliente; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY venda
    ADD CONSTRAINT fk_venda_cliente FOREIGN KEY (cpf_cliente) REFERENCES cliente(cpf);


--
-- TOC entry 1870 (class 2606 OID 41697)
-- Name: fk_venda_func; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY venda
    ADD CONSTRAINT fk_venda_func FOREIGN KEY (id_vendedor) REFERENCES funcionario(id_funcionario);


--
-- TOC entry 1994 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2016-01-24 21:30:54

--
-- PostgreSQL database dump complete
--

