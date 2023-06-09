PGDMP         "                {            reservasi_tiket_bioskop_online    15.2    15.2     "           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            #           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            $           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            %           1262    16398    reservasi_tiket_bioskop_online    DATABASE     �   CREATE DATABASE reservasi_tiket_bioskop_online WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United States.1252';
 .   DROP DATABASE reservasi_tiket_bioskop_online;
                postgres    false            �            1259    16426    film    TABLE     �   CREATE TABLE public.film (
    film_code character varying(10) NOT NULL,
    film_name character varying(50) NOT NULL,
    tayang_atau_tidak character varying(20)
);
    DROP TABLE public.film;
       public         heap    postgres    false            �            1259    16431    jadwal    TABLE       CREATE TABLE public.jadwal (
    id_jadwal integer NOT NULL,
    tanggal_tayang date NOT NULL,
    jam_mulai time with time zone NOT NULL,
    jam_selesai time with time zone NOT NULL,
    harga_tiket integer NOT NULL,
    film_code character varying(10)
);
    DROP TABLE public.jadwal;
       public         heap    postgres    false            �            1259    16516    seats    TABLE     X   CREATE TABLE public.seats (
    nomor_kursi integer NOT NULL,
    studio_name "char"
);
    DROP TABLE public.seats;
       public         heap    postgres    false            �            1259    16460    studio    TABLE     @   CREATE TABLE public.studio (
    studio_name "char" NOT NULL
);
    DROP TABLE public.studio;
       public         heap    postgres    false            �            1259    16448    users    TABLE     �   CREATE TABLE public.users (
    id_user integer NOT NULL,
    username character varying(50) NOT NULL,
    email character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    nomor_kursi integer,
    id_jadwal integer
);
    DROP TABLE public.users;
       public         heap    postgres    false                      0    16426    film 
   TABLE DATA           G   COPY public.film (film_code, film_name, tayang_atau_tidak) FROM stdin;
    public          postgres    false    214   �                  0    16431    jadwal 
   TABLE DATA           k   COPY public.jadwal (id_jadwal, tanggal_tayang, jam_mulai, jam_selesai, harga_tiket, film_code) FROM stdin;
    public          postgres    false    215   x!                 0    16516    seats 
   TABLE DATA           9   COPY public.seats (nomor_kursi, studio_name) FROM stdin;
    public          postgres    false    218   �!                 0    16460    studio 
   TABLE DATA           -   COPY public.studio (studio_name) FROM stdin;
    public          postgres    false    217   "                 0    16448    users 
   TABLE DATA           [   COPY public.users (id_user, username, email, password, nomor_kursi, id_jadwal) FROM stdin;
    public          postgres    false    216   L"       u           2606    16430    film film_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public.film
    ADD CONSTRAINT film_pkey PRIMARY KEY (film_code);
 8   ALTER TABLE ONLY public.film DROP CONSTRAINT film_pkey;
       public            postgres    false    214            x           2606    16435    jadwal jadwal_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.jadwal
    ADD CONSTRAINT jadwal_pkey PRIMARY KEY (id_jadwal);
 <   ALTER TABLE ONLY public.jadwal DROP CONSTRAINT jadwal_pkey;
       public            postgres    false    215            �           2606    16520    seats seats_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.seats
    ADD CONSTRAINT seats_pkey PRIMARY KEY (nomor_kursi);
 :   ALTER TABLE ONLY public.seats DROP CONSTRAINT seats_pkey;
       public            postgres    false    218            �           2606    16522    seats seats_studio_name_key 
   CONSTRAINT     ]   ALTER TABLE ONLY public.seats
    ADD CONSTRAINT seats_studio_name_key UNIQUE (studio_name);
 E   ALTER TABLE ONLY public.seats DROP CONSTRAINT seats_studio_name_key;
       public            postgres    false    218            �           2606    16464    studio studio_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.studio
    ADD CONSTRAINT studio_pkey PRIMARY KEY (studio_name);
 <   ALTER TABLE ONLY public.studio DROP CONSTRAINT studio_pkey;
       public            postgres    false    217            z           2606    16458    users users_email_key 
   CONSTRAINT     Q   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_email_key UNIQUE (email);
 ?   ALTER TABLE ONLY public.users DROP CONSTRAINT users_email_key;
       public            postgres    false    216            |           2606    16536    users users_id_jadwal_key 
   CONSTRAINT     Y   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_id_jadwal_key UNIQUE (id_jadwal);
 C   ALTER TABLE ONLY public.users DROP CONSTRAINT users_id_jadwal_key;
       public            postgres    false    216            ~           2606    16529    users users_nomor_kursi_key 
   CONSTRAINT     ]   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_nomor_kursi_key UNIQUE (nomor_kursi);
 E   ALTER TABLE ONLY public.users DROP CONSTRAINT users_nomor_kursi_key;
       public            postgres    false    216            �           2606    16454    users users_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id_user);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    216            �           2606    16456    users users_username_key 
   CONSTRAINT     W   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_username_key UNIQUE (username);
 B   ALTER TABLE ONLY public.users DROP CONSTRAINT users_username_key;
       public            postgres    false    216            v           1259    24723 	   film_code    INDEX     A   CREATE INDEX film_code ON public.jadwal USING btree (film_code);
    DROP INDEX public.film_code;
       public            postgres    false    215            �           2606    24724    jadwal fk_jadwal_film    FK CONSTRAINT     |   ALTER TABLE ONLY public.jadwal
    ADD CONSTRAINT fk_jadwal_film FOREIGN KEY (film_code) REFERENCES public.film(film_code);
 ?   ALTER TABLE ONLY public.jadwal DROP CONSTRAINT fk_jadwal_film;
       public          postgres    false    215    214    3189            �           2606    16523    seats seats_studio_name_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.seats
    ADD CONSTRAINT seats_studio_name_fkey FOREIGN KEY (studio_name) REFERENCES public.studio(studio_name);
 F   ALTER TABLE ONLY public.seats DROP CONSTRAINT seats_studio_name_fkey;
       public          postgres    false    217    218    3204            �           2606    16537    users users_id_jadwal_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_id_jadwal_fkey FOREIGN KEY (id_jadwal) REFERENCES public.jadwal(id_jadwal);
 D   ALTER TABLE ONLY public.users DROP CONSTRAINT users_id_jadwal_fkey;
       public          postgres    false    215    3192    216            �           2606    16530    users users_nomor_kursi_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_nomor_kursi_fkey FOREIGN KEY (nomor_kursi) REFERENCES public.seats(nomor_kursi);
 F   ALTER TABLE ONLY public.users DROP CONSTRAINT users_nomor_kursi_fkey;
       public          postgres    false    218    216    3206               �   x�e���@D뻯�Ш��C(Ja$���'�\�.ƿ�0h��N��M2�f+j��z:�ҍ�*pM9�>��[��0��50V����v"d�����6�
L&��[׃5~:ɻ�(U�)��^M��+r]�M��t=�!����]@�Ah��u-�|6GF@         j   x����	�@E�u�y�)�K��:�G�,����@4�2�XC<�#���hu �����z�ă����^�ZP�\�Un�ع�{��ι��^�����B�2>            x�3���2� "F��� 'Tv         #   x�s�r�r�r�r�r�r������������� <         �   x�]��
�0@��ǌ��u��0��E�K��Q���������� T����@k�t�iD!�ݡsd���x���J+,;0J�USބ�M��s&��yL6�m��#u4�^r����-����N�)�V���K�b�;=��;�{[�xq��c���Q!     