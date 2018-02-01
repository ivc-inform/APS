--
-- PostgreSQL database dump
--

-- Dumped from database version 10.1
-- Dumped by pg_dump version 10.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: postgres; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON DATABASE postgres IS 'default administrative connection database';


--
-- Name: aps; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA aps;


ALTER SCHEMA aps OWNER TO postgres;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = aps, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: user; Type: TABLE; Schema: aps; Owner: postgres
--

CREATE TABLE "user" (
    di bigint NOT NULL,
    login character varying(255) NOT NULL,
    firstname character varying(255),
    secondname character varying(255),
    lastname character varying(255) NOT NULL,
    active boolean NOT NULL,
    group_id bigint NOT NULL
);


ALTER TABLE "user" OWNER TO postgres;

--
-- Name: TABLE "user"; Type: COMMENT; Schema: aps; Owner: postgres
--

COMMENT ON TABLE "user" IS 'Пользователи';


--
-- Name: COLUMN "user".di; Type: COMMENT; Schema: aps; Owner: postgres
--

COMMENT ON COLUMN "user".di IS 'Идентификатор пользователя';


--
-- Name: COLUMN "user".login; Type: COMMENT; Schema: aps; Owner: postgres
--

COMMENT ON COLUMN "user".login IS 'Логин';


--
-- Name: COLUMN "user".firstname; Type: COMMENT; Schema: aps; Owner: postgres
--

COMMENT ON COLUMN "user".firstname IS 'Имя';


--
-- Name: COLUMN "user".secondname; Type: COMMENT; Schema: aps; Owner: postgres
--

COMMENT ON COLUMN "user".secondname IS 'Отчество';


--
-- Name: COLUMN "user".lastname; Type: COMMENT; Schema: aps; Owner: postgres
--

COMMENT ON COLUMN "user".lastname IS 'Фамилия';


--
-- Name: COLUMN "user".active; Type: COMMENT; Schema: aps; Owner: postgres
--

COMMENT ON COLUMN "user".active IS 'Активность пользователя';


--
-- Name: usergroup; Type: TABLE; Schema: aps; Owner: postgres
--

CREATE TABLE usergroup (
    di bigint NOT NULL,
    codegroup character varying(255),
    "captionGroup" character varying(255),
    "descriptionGroup" character varying(2000),
    active boolean NOT NULL
);


ALTER TABLE usergroup OWNER TO postgres;

--
-- Name: TABLE usergroup; Type: COMMENT; Schema: aps; Owner: postgres
--

COMMENT ON TABLE usergroup IS 'Группы пользователей';


--
-- Name: COLUMN usergroup.di; Type: COMMENT; Schema: aps; Owner: postgres
--

COMMENT ON COLUMN usergroup.di IS 'Идентификатор группы';


--
-- Name: COLUMN usergroup.codegroup; Type: COMMENT; Schema: aps; Owner: postgres
--

COMMENT ON COLUMN usergroup.codegroup IS 'Код группы';


--
-- Name: COLUMN usergroup."captionGroup"; Type: COMMENT; Schema: aps; Owner: postgres
--

COMMENT ON COLUMN usergroup."captionGroup" IS 'Наименование группы';


--
-- Name: COLUMN usergroup."descriptionGroup"; Type: COMMENT; Schema: aps; Owner: postgres
--

COMMENT ON COLUMN usergroup."descriptionGroup" IS 'Описание группы';


--
-- Name: COLUMN usergroup.active; Type: COMMENT; Schema: aps; Owner: postgres
--

COMMENT ON COLUMN usergroup.active IS 'Активность пользователя';


--
-- Data for Name: user; Type: TABLE DATA; Schema: aps; Owner: postgres
--

COPY "user" (di, login, firstname, secondname, lastname, active, group_id) FROM stdin;
\.


--
-- Data for Name: usergroup; Type: TABLE DATA; Schema: aps; Owner: postgres
--

COPY usergroup (di, codegroup, "captionGroup", "descriptionGroup", active) FROM stdin;
\.


--
-- Name: user user_pk; Type: CONSTRAINT; Schema: aps; Owner: postgres
--

ALTER TABLE ONLY "user"
    ADD CONSTRAINT user_pk PRIMARY KEY (di);


--
-- Name: usergroup usergroup_pk; Type: CONSTRAINT; Schema: aps; Owner: postgres
--

ALTER TABLE ONLY usergroup
    ADD CONSTRAINT usergroup_pk PRIMARY KEY (di);


--
-- Name: user_group_idx; Type: INDEX; Schema: aps; Owner: postgres
--

CREATE INDEX user_group_idx ON "user" USING btree (group_id);


--
-- Name: user user_usergroup; Type: FK CONSTRAINT; Schema: aps; Owner: postgres
--

ALTER TABLE ONLY "user"
    ADD CONSTRAINT user_usergroup FOREIGN KEY (group_id) REFERENCES usergroup(di);


--
-- PostgreSQL database dump complete
--

