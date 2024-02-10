PGDMP  /                     |            postgres    16.1    16.0 e    `           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            a           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            b           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            c           1262    5    postgres    DATABASE     �   CREATE DATABASE postgres WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United States.1252';
    DROP DATABASE postgres;
                postgres    false            d           0    0    DATABASE postgres    COMMENT     N   COMMENT ON DATABASE postgres IS 'default administrative connection database';
                   postgres    false    4963                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
                pg_database_owner    false            e           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                   pg_database_owner    false    5            �            1259    24578    account    TABLE     )  CREATE TABLE public.account (
    id bigint NOT NULL,
    owner bigint NOT NULL,
    name character varying NOT NULL,
    created_on timestamp without time zone,
    updated_on timestamp without time zone,
    author_id bigint NOT NULL,
    updated_by bigint,
    external_id character varying
);
    DROP TABLE public.account;
       public         heap    postgres    false    5            �            1259    24583    account_id_seq    SEQUENCE     �   ALTER TABLE public.account ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.account_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    217    5            �            1259    24584    departments    TABLE     �   CREATE TABLE public.departments (
    id bigint NOT NULL,
    name character varying NOT NULL,
    code character varying NOT NULL,
    office_id bigint NOT NULL,
    parent_department bigint
);
    DROP TABLE public.departments;
       public         heap    postgres    false    5            �            1259    24589    employee_station    TABLE     �   CREATE TABLE public.employee_station (
    id bigint NOT NULL,
    employee_id bigint NOT NULL,
    office_id bigint NOT NULL,
    station_id bigint
);
 $   DROP TABLE public.employee_station;
       public         heap    postgres    false    5            �            1259    24592 	   employees    TABLE       CREATE TABLE public.employees (
    id bigint NOT NULL,
    names character varying,
    gender character varying,
    created_on timestamp without time zone,
    updated_on timestamp without time zone,
    author_id bigint,
    updated_by bigint,
    account_id bigint
);
    DROP TABLE public.employees;
       public         heap    postgres    false    5            �            1259    24597    guest    TABLE     �   CREATE TABLE public.guest (
    id bigint NOT NULL,
    guest_profile_id bigint,
    author_id bigint,
    created_on timestamp without time zone,
    updated_by bigint,
    date_updated timestamp without time zone
);
    DROP TABLE public.guest;
       public         heap    postgres    false    5            �            1259    24600    guest_id_seq    SEQUENCE     �   ALTER TABLE public.guest ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.guest_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    5    222            �            1259    24601    kiosks    TABLE     g  CREATE TABLE public.kiosks (
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
    DROP TABLE public.kiosks;
       public         heap    postgres    false    5            �            1259    24607    office_station    TABLE     u   CREATE TABLE public.office_station (
    id bigint NOT NULL,
    office_id bigint NOT NULL,
    station_id bigint
);
 "   DROP TABLE public.office_station;
       public         heap    postgres    false    5            �            1259    24610    offices    TABLE     C  CREATE TABLE public.offices (
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
    DROP TABLE public.offices;
       public         heap    postgres    false    5            �            1259    24615    organisations    TABLE       CREATE TABLE public.organisations (
    id bigint NOT NULL,
    name character varying(255),
    owner bigint,
    date_created timestamp with time zone,
    author_id bigint,
    date_updated timestamp with time zone,
    updated_by bigint,
    details character varying(500)
);
 !   DROP TABLE public.organisations;
       public         heap    postgres    false    5            f           0    0    TABLE organisations    COMMENT     A   COMMENT ON TABLE public.organisations IS 'Manage Organisations';
          public          postgres    false    227            �            1259    24620    permission_scope    TABLE     y   CREATE TABLE public.permission_scope (
    id bigint NOT NULL,
    key character varying,
    value character varying
);
 $   DROP TABLE public.permission_scope;
       public         heap    postgres    false    5            �            1259    24625    permissions    TABLE     �   CREATE TABLE public.permissions (
    id bigint NOT NULL,
    code character varying,
    name character varying,
    "grouping" character varying
);
    DROP TABLE public.permissions;
       public         heap    postgres    false    5            �            1259    24630    permissions_id_seq    SEQUENCE     �   ALTER TABLE public.permissions ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.permissions_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    229    5            �            1259    24631    play_evolutions    TABLE       CREATE TABLE public.play_evolutions (
    id integer NOT NULL,
    hash character varying(255) NOT NULL,
    applied_at timestamp without time zone NOT NULL,
    apply_script text,
    revert_script text,
    state character varying(255),
    last_problem text
);
 #   DROP TABLE public.play_evolutions;
       public         heap    postgres    false    5            �            1259    24636    profile    TABLE     [  CREATE TABLE public.profile (
    id bigint NOT NULL,
    user_id bigint,
    firstname character varying,
    othernames character varying,
    created_at time without time zone NOT NULL,
    created_by bigint,
    updated_at timestamp without time zone,
    gender character varying,
    profile_type character varying,
    updated_by bigint
);
    DROP TABLE public.profile;
       public         heap    postgres    false    5            �            1259    24641    profile_id_seq    SEQUENCE     �   ALTER TABLE public.profile ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.profile_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    232    5            �            1259    24642    resident    TABLE       CREATE TABLE public.resident (
    id bigint NOT NULL,
    profile_id bigint,
    station_id bigint,
    join_date date,
    author_id bigint,
    created_on timestamp without time zone,
    updated_by bigint,
    date_updated timestamp without time zone
);
    DROP TABLE public.resident;
       public         heap    postgres    false    5            �            1259    24645    resident_id_seq    SEQUENCE     �   ALTER TABLE public.resident ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.resident_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
    CYCLE
);
            public          postgres    false    5    234            �            1259    24646    role    TABLE     P  CREATE TABLE public.role (
    id bigint NOT NULL,
    code character varying(50) NOT NULL,
    name character varying(200) NOT NULL,
    is_system boolean NOT NULL,
    created_on timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_on timestamp without time zone,
    author_id bigint,
    updated_by bigint
);
    DROP TABLE public.role;
       public         heap    postgres    false    5            �            1259    24650    role_id_seq    SEQUENCE     �   ALTER TABLE public.role ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    5    236            �            1259    24651    role_permission    TABLE     #  CREATE TABLE public.role_permission (
    id bigint NOT NULL,
    role_id bigint NOT NULL,
    permission_id bigint NOT NULL,
    created_on timestamp without time zone NOT NULL,
    updated_on timestamp without time zone,
    author_id bigint,
    updated_by bigint,
    scope_id bigint
);
 #   DROP TABLE public.role_permission;
       public         heap    postgres    false    5            �            1259    24654    stations    TABLE     5  CREATE TABLE public.stations (
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
    DROP TABLE public.stations;
       public         heap    postgres    false    5            �            1259    24659    stations_id_seq    SEQUENCE     �   ALTER TABLE public.stations ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.stations_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    5    239            �            1259    24660    user    TABLE     w   CREATE TABLE public."user" (
    id bigint NOT NULL,
    username character varying,
    password character varying
);
    DROP TABLE public."user";
       public         heap    postgres    false    5            �            1259    24665    user_id_seq    SEQUENCE     �   ALTER TABLE public."user" ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    241    5            �            1259    24666 	   user_role    TABLE     $  CREATE TABLE public.user_role (
    id bigint NOT NULL,
    user_id bigint NOT NULL,
    role_id bigint NOT NULL,
    status character varying(50) NOT NULL,
    author_id bigint,
    created_on timestamp without time zone,
    updated_on timestamp without time zone,
    updated_by bigint
);
    DROP TABLE public.user_role;
       public         heap    postgres    false    5            �            1259    24669    user_role_id_seq    SEQUENCE     �   ALTER TABLE public.user_role ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.user_role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    243    5            �            1259    24670    users    TABLE     :  CREATE TABLE public.users (
    id bigint NOT NULL,
    username character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    author_id bigint,
    created_on timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_on timestamp without time zone,
    updated_by bigint
);
    DROP TABLE public.users;
       public         heap    postgres    false    5            g           0    0    TABLE users    COMMENT     1   COMMENT ON TABLE public.users IS 'Manage Users';
          public          postgres    false    245            �            1259    24676    users_id_seq    SEQUENCE     �   ALTER TABLE public.users ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    245    5            �            1259    24677    visitationrequests    TABLE     �  CREATE TABLE public.visitationrequests (
    id bigint NOT NULL,
    host_id bigint,
    guest_id bigint,
    office_id bigint,
    department_id bigint,
    date_created timestamp without time zone,
    created_by bigint,
    date_updated timestamp without time zone,
    updated_by bigint,
    start_date timestamp without time zone,
    end_date timestamp without time zone,
    inv_type character varying,
    status character varying
);
 &   DROP TABLE public.visitationrequests;
       public         heap    postgres    false    5            �            1259    24682    visitationrequests_id_seq    SEQUENCE     �   ALTER TABLE public.visitationrequests ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.visitationrequests_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    247    5            �            1259    24683    visitations    TABLE     �  CREATE TABLE public.visitations (
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
    DROP TABLE public.visitations;
       public         heap    postgres    false    5            �            1259    24689    visitations_id_seq    SEQUENCE     �   ALTER TABLE public.visitations ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.visitations_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    249    5            <          0    24578    account 
   TABLE DATA           n   COPY public.account (id, owner, name, created_on, updated_on, author_id, updated_by, external_id) FROM stdin;
    public          postgres    false    217   �z       >          0    24584    departments 
   TABLE DATA           S   COPY public.departments (id, name, code, office_id, parent_department) FROM stdin;
    public          postgres    false    219   {       ?          0    24589    employee_station 
   TABLE DATA           R   COPY public.employee_station (id, employee_id, office_id, station_id) FROM stdin;
    public          postgres    false    220   {       @          0    24592 	   employees 
   TABLE DATA           q   COPY public.employees (id, names, gender, created_on, updated_on, author_id, updated_by, account_id) FROM stdin;
    public          postgres    false    221   ;{       A          0    24597    guest 
   TABLE DATA           f   COPY public.guest (id, guest_profile_id, author_id, created_on, updated_by, date_updated) FROM stdin;
    public          postgres    false    222   X{       C          0    24601    kiosks 
   TABLE DATA           �   COPY public.kiosks (id, reference, details, device_token, date_created, date_updated, author_id, updated_by, station_id) FROM stdin;
    public          postgres    false    224   u{       D          0    24607    office_station 
   TABLE DATA           C   COPY public.office_station (id, office_id, station_id) FROM stdin;
    public          postgres    false    225   �{       E          0    24610    offices 
   TABLE DATA           �   COPY public.offices (id, name, dateopened, parent_office, created_on, updated_on, author_id, updated_by, account_id) FROM stdin;
    public          postgres    false    226   �{       F          0    24615    organisations 
   TABLE DATA           t   COPY public.organisations (id, name, owner, date_created, author_id, date_updated, updated_by, details) FROM stdin;
    public          postgres    false    227   �{       G          0    24620    permission_scope 
   TABLE DATA           :   COPY public.permission_scope (id, key, value) FROM stdin;
    public          postgres    false    228   �{       H          0    24625    permissions 
   TABLE DATA           A   COPY public.permissions (id, code, name, "grouping") FROM stdin;
    public          postgres    false    229   |       J          0    24631    play_evolutions 
   TABLE DATA           q   COPY public.play_evolutions (id, hash, applied_at, apply_script, revert_script, state, last_problem) FROM stdin;
    public          postgres    false    231   #|       K          0    24636    profile 
   TABLE DATA           �   COPY public.profile (id, user_id, firstname, othernames, created_at, created_by, updated_at, gender, profile_type, updated_by) FROM stdin;
    public          postgres    false    232   �|       M          0    24642    resident 
   TABLE DATA           z   COPY public.resident (id, profile_id, station_id, join_date, author_id, created_on, updated_by, date_updated) FROM stdin;
    public          postgres    false    234   }       O          0    24646    role 
   TABLE DATA           h   COPY public.role (id, code, name, is_system, created_on, updated_on, author_id, updated_by) FROM stdin;
    public          postgres    false    236   !}       Q          0    24651    role_permission 
   TABLE DATA           ~   COPY public.role_permission (id, role_id, permission_id, created_on, updated_on, author_id, updated_by, scope_id) FROM stdin;
    public          postgres    false    238   >}       R          0    24654    stations 
   TABLE DATA           �   COPY public.stations (id, organisation_id, name, code, location, date_created, date_updated, author_id, updated_by) FROM stdin;
    public          postgres    false    239   [}       T          0    24660    user 
   TABLE DATA           8   COPY public."user" (id, username, password) FROM stdin;
    public          postgres    false    241   x}       V          0    24666 	   user_role 
   TABLE DATA           p   COPY public.user_role (id, user_id, role_id, status, author_id, created_on, updated_on, updated_by) FROM stdin;
    public          postgres    false    243   �}       X          0    24670    users 
   TABLE DATA           f   COPY public.users (id, username, password, author_id, created_on, updated_on, updated_by) FROM stdin;
    public          postgres    false    245   �}       Z          0    24677    visitationrequests 
   TABLE DATA           �   COPY public.visitationrequests (id, host_id, guest_id, office_id, department_id, date_created, created_by, date_updated, updated_by, start_date, end_date, inv_type, status) FROM stdin;
    public          postgres    false    247   �}       \          0    24683    visitations 
   TABLE DATA           �   COPY public.visitations (id, "guestId", hostid, time_in, time_out, station_id, kiosk_id, status, reference_id, officeid, departmentid) FROM stdin;
    public          postgres    false    249   �~       h           0    0    account_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.account_id_seq', 1, false);
          public          postgres    false    218            i           0    0    guest_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.guest_id_seq', 1, false);
          public          postgres    false    223            j           0    0    permissions_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.permissions_id_seq', 1, false);
          public          postgres    false    230            k           0    0    profile_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.profile_id_seq', 1, false);
          public          postgres    false    233            l           0    0    resident_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.resident_id_seq', 1, false);
          public          postgres    false    235            m           0    0    role_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.role_id_seq', 1, false);
          public          postgres    false    237            n           0    0    stations_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.stations_id_seq', 1, false);
          public          postgres    false    240            o           0    0    user_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.user_id_seq', 1, false);
          public          postgres    false    242            p           0    0    user_role_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.user_role_id_seq', 1, false);
          public          postgres    false    244            q           0    0    users_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.users_id_seq', 1, false);
          public          postgres    false    246            r           0    0    visitationrequests_id_seq    SEQUENCE SET     H   SELECT pg_catalog.setval('public.visitationrequests_id_seq', 10, true);
          public          postgres    false    248            s           0    0    visitations_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.visitations_id_seq', 1, false);
          public          postgres    false    250            �           2606    24691    account account_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.account
    ADD CONSTRAINT account_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.account DROP CONSTRAINT account_pkey;
       public            postgres    false    217            �           2606    24693    resident big integer 
   CONSTRAINT     a   ALTER TABLE ONLY public.resident
    ADD CONSTRAINT "big integer" PRIMARY KEY (id) INCLUDE (id);
 @   ALTER TABLE ONLY public.resident DROP CONSTRAINT "big integer";
       public            postgres    false    234            �           2606    24695    departments departments_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.departments
    ADD CONSTRAINT departments_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.departments DROP CONSTRAINT departments_pkey;
       public            postgres    false    219            �           2606    24697 &   employee_station employee_station_pkey 
   CONSTRAINT     d   ALTER TABLE ONLY public.employee_station
    ADD CONSTRAINT employee_station_pkey PRIMARY KEY (id);
 P   ALTER TABLE ONLY public.employee_station DROP CONSTRAINT employee_station_pkey;
       public            postgres    false    220            �           2606    24699    employees employees_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.employees
    ADD CONSTRAINT employees_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.employees DROP CONSTRAINT employees_pkey;
       public            postgres    false    221            �           2606    24701    guest guest_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.guest
    ADD CONSTRAINT guest_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.guest DROP CONSTRAINT guest_pkey;
       public            postgres    false    222            �           2606    24703    organisations id 
   CONSTRAINT     N   ALTER TABLE ONLY public.organisations
    ADD CONSTRAINT id PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.organisations DROP CONSTRAINT id;
       public            postgres    false    227            �           2606    24705    kiosks kiosks_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.kiosks
    ADD CONSTRAINT kiosks_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.kiosks DROP CONSTRAINT kiosks_pkey;
       public            postgres    false    224            �           2606    24707 "   office_station office_station_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public.office_station
    ADD CONSTRAINT office_station_pkey PRIMARY KEY (id);
 L   ALTER TABLE ONLY public.office_station DROP CONSTRAINT office_station_pkey;
       public            postgres    false    225            �           2606    24709    offices offices_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.offices
    ADD CONSTRAINT offices_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.offices DROP CONSTRAINT offices_pkey;
       public            postgres    false    226            �           2606    24711 
   user p_key 
   CONSTRAINT     J   ALTER TABLE ONLY public."user"
    ADD CONSTRAINT p_key PRIMARY KEY (id);
 6   ALTER TABLE ONLY public."user" DROP CONSTRAINT p_key;
       public            postgres    false    241            �           2606    24713 &   permission_scope permission_scope_pkey 
   CONSTRAINT     d   ALTER TABLE ONLY public.permission_scope
    ADD CONSTRAINT permission_scope_pkey PRIMARY KEY (id);
 P   ALTER TABLE ONLY public.permission_scope DROP CONSTRAINT permission_scope_pkey;
       public            postgres    false    228            �           2606    24715    permissions permissions_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.permissions
    ADD CONSTRAINT permissions_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.permissions DROP CONSTRAINT permissions_pkey;
       public            postgres    false    229            �           2606    24717 $   play_evolutions play_evolutions_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.play_evolutions
    ADD CONSTRAINT play_evolutions_pkey PRIMARY KEY (id);
 N   ALTER TABLE ONLY public.play_evolutions DROP CONSTRAINT play_evolutions_pkey;
       public            postgres    false    231            �           2606    24719    profile profile_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.profile
    ADD CONSTRAINT profile_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.profile DROP CONSTRAINT profile_pkey;
       public            postgres    false    232            �           2606    24721 $   role_permission role_permission_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.role_permission
    ADD CONSTRAINT role_permission_pkey PRIMARY KEY (id);
 N   ALTER TABLE ONLY public.role_permission DROP CONSTRAINT role_permission_pkey;
       public            postgres    false    238            �           2606    24723    role role_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.role DROP CONSTRAINT role_pkey;
       public            postgres    false    236            �           2606    24725    stations stations_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.stations
    ADD CONSTRAINT stations_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.stations DROP CONSTRAINT stations_pkey;
       public            postgres    false    239            �           2606    24727    user_role user_role_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.user_role
    ADD CONSTRAINT user_role_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.user_role DROP CONSTRAINT user_role_pkey;
       public            postgres    false    243            �           2606    24729    users users_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    245            �           2606    24731 *   visitationrequests visitationrequests_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY public.visitationrequests
    ADD CONSTRAINT visitationrequests_pkey PRIMARY KEY (id);
 T   ALTER TABLE ONLY public.visitationrequests DROP CONSTRAINT visitationrequests_pkey;
       public            postgres    false    247            �           2606    24733    visitations visitations_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.visitations
    ADD CONSTRAINT visitations_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.visitations DROP CONSTRAINT visitations_pkey;
       public            postgres    false    249            �           2606    24734    visitationrequests FK_Host    FK CONSTRAINT     �   ALTER TABLE ONLY public.visitationrequests
    ADD CONSTRAINT "FK_Host" FOREIGN KEY (host_id) REFERENCES public.profile(id) NOT VALID;
 F   ALTER TABLE ONLY public.visitationrequests DROP CONSTRAINT "FK_Host";
       public          postgres    false    247    232    4760            �           2606    24739    visitationrequests FK_guest    FK CONSTRAINT     �   ALTER TABLE ONLY public.visitationrequests
    ADD CONSTRAINT "FK_guest" FOREIGN KEY (guest_id) REFERENCES public.profile(id) NOT VALID;
 G   ALTER TABLE ONLY public.visitationrequests DROP CONSTRAINT "FK_guest";
       public          postgres    false    232    4760    247            <      x������ � �      >      x������ � �      ?      x������ � �      @      x������ � �      A      x������ � �      C      x������ � �      D      x������ � �      E      x������ � �      F      x������ � �      G      x������ � �      H      x������ � �      J   [   x���1�  �_�hĊo��&��Ե{��a���xe�d˗J��m͆'*G�H���{�I�����>!�ϭ�w�R��&�      K   f   x�32����-M�,�HT�t�I��+N�4��22�20�3�4511�)K�I���/.r�� :s2�RʁZ�JK���Z��L�M-,�����B���qqq 5       M      x������ � �      O      x������ � �      Q      x������ � �      R      x������ � �      T      x������ � �      V      x������ � �      X      x������ � �      Z   �   x���K� ൞�8��ػ0i�i����>�65�d`�!�X*�jSYZ�-�1pAоw�㵿ݧ���Pův�(4�!	mۗ3�F<����Ӽ����2��x�>i&�ŨBg�	��k6P"�fK�x��>��][yW�M���O�y�`��<�y¬��"�C�����u]? ��y      \      x������ � �     