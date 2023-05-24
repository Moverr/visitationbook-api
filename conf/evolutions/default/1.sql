

CREATE TABLE IF NOT EXISTS account (
    id bigint NOT NULL,
    owner bigint NOT NULL,
    name character varying NOT NULL,
    created_on timestamp without time zone,
    updated_on timestamp without time zone,
    author_id bigint NOT NULL,
    updated_by bigint,
    external_id character varying
);




ALTER TABLE account ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME  account_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);




CREATE TABLE  IF NOT EXISTS departments (
    id bigint NOT NULL,
    name character varying NOT NULL,
    code character varying NOT NULL,
    office_id bigint NOT NULL,
    parent_department bigint
);



CREATE TABLE  IF NOT EXISTS employee_station (
    id bigint NOT NULL,
    employee_id bigint NOT NULL,
    office_id bigint NOT NULL,
    station_id bigint
);




CREATE TABLE  IF NOT EXISTS employees (
    id bigint NOT NULL,
    names character varying,
    gender character varying,
    created_on timestamp without time zone,
    updated_on timestamp without time zone,
    author_id bigint,
    updated_by bigint,
    account_id bigint
);



CREATE TABLE IF NOT EXISTS guest (
    id bigint NOT NULL,
    guest_profile_id bigint,
    author_id bigint,
    created_on timestamp without time zone,
    updated_by bigint,
    date_updated timestamp without time zone
);



ALTER TABLE  guest ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME  guest_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);



CREATE TABLE  IF NOT EXISTS kiosks (
    id bigint NOT NULL,
    reference character varying NOT NULL,
    details character varying,
    device_token character varying,
    date_created timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    date_updated timestamp without time zone,
    author_id bigint,
    updated_by bigint,
    station_id bigint
);



ALTER TABLE  IF NOT EXISTS kiosks ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME  kiosks_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);



CREATE TABLE  IF NOT EXISTS office_station (
    id bigint NOT NULL,
    office_id bigint NOT NULL,
    station_id bigint
);



CREATE TABLE  IF NOT EXISTS offices (
    id bigint NOT NULL,
    name character varying NOT NULL,
    dateopened date NOT NULL,
    parent_office bigint,
    created_on timestamp without time zone NOT NULL,
    updated_on timestamp without time zone,
    author_id bigint,
    updated_by bigint,
    account_id bigint NOT NULL
);




CREATE TABLE  IF NOT EXISTS organisations (
    id bigint NOT NULL,
    name character varying(255),
    owner bigint,
    date_created timestamp with time zone,
    author_id bigint,
    date_updated timestamp with time zone,
    updated_by bigint,
    details character varying(500)
);




COMMENT ON TABLE  organisations IS 'Manage Organisations';




ALTER TABLE  IF NOT EXISTS organisations ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME  organisations_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);




CREATE TABLE  IF NOT EXISTS permission_scope (
    id bigint NOT NULL,
    key character varying,
    value character varying
);


CREATE TABLE  IF NOT EXISTS permissions (
    id bigint NOT NULL,
    code character varying,
    name character varying,
    "grouping" character varying
);




ALTER TABLE  permissions ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME  permissions_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);



CREATE TABLE  IF NOT EXISTS profile (
    id bigint NOT NULL,
    user_id bigint,
    surname character varying,
    other_names character varying,
    author_id bigint,
    created_on time without time zone NOT NULL,
    updated_by bigint,
    date_updated timestamp without time zone,
    gender character varying,
    profile_type character varying
);



ALTER TABLE  profile ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME  profile_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);




CREATE TABLE IF NOT EXISTS resident (
    id bigint NOT NULL,
    profile_id bigint,
    station_id bigint,
    join_date date,
    author_id bigint,
    created_on timestamp without time zone,
    updated_by bigint,
    date_updated timestamp without time zone
);




ALTER TABLE  resident ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME  resident_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
    CYCLE
);



CREATE TABLE IF NOT EXISTS role (
    id bigint NOT NULL,
    code character varying(50) NOT NULL,
    name character varying(200) NOT NULL,
    is_system boolean NOT NULL,
    created_on timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_on timestamp without time zone,
    author_id bigint,
    updated_by bigint
);




ALTER TABLE  role ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME  role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);



CREATE TABLE  IF NOT EXISTS role_permission (
    id bigint NOT NULL,
    role_id bigint NOT NULL,
    permission_id bigint NOT NULL,
    created_on timestamp without time zone NOT NULL,
    updated_on timestamp without time zone,
    author_id bigint,
    updated_by bigint,
    scope_id bigint
);




CREATE TABLE IF NOT EXISTS stations (
    id bigint NOT NULL,
    organisation_id bigint NOT NULL,
    name character varying,
    code character varying,
    location character varying,
    date_created time without time zone,
    date_updated time without time zone,
    author_id bigint,
    updated_by bigint
);




ALTER TABLE stations ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME  stations_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);



CREATE TABLE  "user" (
    id bigint NOT NULL,
    username character varying,
    password character varying
);




ALTER TABLE  "user" ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME  user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);



CREATE TABLE IF NOT EXISTS user_role (
    id bigint NOT NULL,
    user_id bigint NOT NULL,
    role_id bigint NOT NULL,
    status character varying(50) NOT NULL,
    author_id bigint,
    created_on timestamp without time zone,
    updated_on timestamp without time zone,
    updated_by bigint
);



ALTER TABLE  user_role ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME  user_role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);



CREATE TABLE  IF NOT EXISTS users (
    id bigint NOT NULL,
    username character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    author_id bigint,
    created_on timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_on timestamp without time zone,
    updated_by bigint
);



COMMENT ON TABLE  users IS 'Manage Users';




ALTER TABLE  users ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME  users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);




CREATE TABLE  IF NOT EXISTS visitationrequests (
    id bigint NOT NULL,
    hostid bigint,
    guestid bigint,
    officeid bigint,
    departmentid bigint,
    invtype "char"[],
    datecreated timestamp without time zone,
    createdby bigint,
    dateupdated timestamp without time zone,
    updatedby bigint
);




CREATE TABLE  IF NOT EXISTS visitations (
    id bigint NOT NULL,
    "guestId" bigint,
    hostid bigint,
    time_in timestamp without time zone,
    time_out timestamp without time zone,
    station_id bigint,
    kiosk_id bigint,
    status character varying(30) DEFAULT 'pending'::character varying NOT NULL,
    reference_id character varying,
    officeid bigint,
    departmentid bigint
);




ALTER TABLE  visitations ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME  visitations_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);



ALTER TABLE ONLY  account
    ADD CONSTRAINT account_pkey PRIMARY KEY (id);


ALTER TABLE ONLY  resident
    ADD CONSTRAINT "big integer" PRIMARY KEY (id) INCLUDE (id);



ALTER TABLE ONLY  departments
    ADD CONSTRAINT departments_pkey PRIMARY KEY (id);



ALTER TABLE ONLY  employee_station
    ADD CONSTRAINT employee_station_pkey PRIMARY KEY (id);


ALTER TABLE ONLY  employees
    ADD CONSTRAINT employees_pkey PRIMARY KEY (id);



ALTER TABLE ONLY  guest
    ADD CONSTRAINT guest_pkey PRIMARY KEY (id);

ALTER TABLE ONLY  organisations
    ADD CONSTRAINT id PRIMARY KEY (id);


ALTER TABLE ONLY  kiosks
    ADD CONSTRAINT kiosks_pkey PRIMARY KEY (id);



ALTER TABLE ONLY  office_station
    ADD CONSTRAINT office_station_pkey PRIMARY KEY (id);



ALTER TABLE ONLY  offices
    ADD CONSTRAINT offices_pkey PRIMARY KEY (id);


ALTER TABLE ONLY  "user"
    ADD CONSTRAINT p_key PRIMARY KEY (id);




ALTER TABLE ONLY  permission_scope
    ADD CONSTRAINT permission_scope_pkey PRIMARY KEY (id);



ALTER TABLE ONLY  permissions
    ADD CONSTRAINT permissions_pkey PRIMARY KEY (id);


ALTER TABLE ONLY  profile
    ADD CONSTRAINT profile_pkey PRIMARY KEY (id);



ALTER TABLE ONLY  role_permission
    ADD CONSTRAINT role_permission_pkey PRIMARY KEY (id);


ALTER TABLE ONLY  role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);



ALTER TABLE ONLY  stations
    ADD CONSTRAINT stations_pkey PRIMARY KEY (id);



ALTER TABLE ONLY  user_role
    ADD CONSTRAINT user_role_pkey PRIMARY KEY (id);



ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);



ALTER TABLE ONLY  visitationrequests
    ADD CONSTRAINT visitationrequests_pkey PRIMARY KEY (id);



ALTER TABLE ONLY  visitations
    ADD CONSTRAINT visitations_pkey PRIMARY KEY (id);


ALTER TABLE ONLY  resident
    ADD CONSTRAINT "PROF" FOREIGN KEY (profile_id) REFERENCES "default".profile(id) NOT VALID;



ALTER TABLE ONLY  offices
    ADD CONSTRAINT account FOREIGN KEY (account_id) REFERENCES "default".account(id) NOT VALID;




ALTER TABLE ONLY  employees
    ADD CONSTRAINT account FOREIGN KEY (account_id) REFERENCES "default".account(id) NOT VALID;



ALTER TABLE ONLY  account
    ADD CONSTRAINT author_id FOREIGN KEY (author_id) REFERENCES "default".users(id) NOT VALID;



ALTER TABLE ONLY  visitations
    ADD CONSTRAINT guest_fk_profile FOREIGN KEY ("guestId") REFERENCES "default".profile(id) NOT VALID;


--
-- TOC entry 2869 (class 2606 OID 201680)
-- Name: guest guest_guest_profile_id_fkey; Type: FK CONSTRAINT; Schema: default; Owner: postgres
--

ALTER TABLE ONLY   guest
    ADD CONSTRAINT guest_guest_profile_id_fkey FOREIGN KEY (guest_profile_id) REFERENCES "default".profile(id) NOT VALID;




ALTER TABLE ONLY  visitations
    ADD CONSTRAINT host_fk_profile FOREIGN KEY (hostid) REFERENCES "default".profile(id) NOT VALID;



ALTER TABLE ONLY  office_station
    ADD CONSTRAINT office FOREIGN KEY (office_id) REFERENCES "default".offices(id) NOT VALID;



ALTER TABLE ONLY  stations
    ADD CONSTRAINT organisation_id FOREIGN KEY (organisation_id) REFERENCES "default".organisations(id) NOT VALID;



ALTER TABLE ONLY  account
    ADD CONSTRAINT owner FOREIGN KEY (owner) REFERENCES "default".users(id);


ALTER TABLE ONLY  role_permission
    ADD CONSTRAINT permission_id FOREIGN KEY (permission_id) REFERENCES "default".permissions(id) NOT VALID;


ALTER TABLE ONLY  user_role
    ADD CONSTRAINT role FOREIGN KEY (role_id) REFERENCES "default".role(id) NOT VALID;


ALTER TABLE ONLY  role_permission
    ADD CONSTRAINT role_id FOREIGN KEY (role_id) REFERENCES "default".role(id) NOT VALID;



ALTER TABLE ONLY  role_permission
    ADD CONSTRAINT scope_id FOREIGN KEY (scope_id) REFERENCES "default".permission_scope(id) NOT VALID;



ALTER TABLE ONLY  office_station
    ADD CONSTRAINT station FOREIGN KEY (station_id) REFERENCES "default".stations(id) NOT VALID;



ALTER TABLE ONLY  account
    ADD CONSTRAINT updated_by FOREIGN KEY (updated_by) REFERENCES "default".users(id) NOT VALID;


ALTER TABLE ONLY  user_role
    ADD CONSTRAINT "user" FOREIGN KEY (user_id) REFERENCES "default".users(id) NOT VALID;


ALTER TABLE ONLY  profile
    ADD CONSTRAINT user_id FOREIGN KEY (user_id) REFERENCES "default".users(id) NOT VALID;

