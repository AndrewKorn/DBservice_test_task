SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

CREATE TABLE public.customer (
                                 id bigint NOT NULL,
                                 first_name text NOT NULL,
                                 last_name text NOT NULL
);


ALTER TABLE public.customer OWNER TO postgres;


ALTER TABLE public.customer ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.customer_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);

CREATE TABLE public.product (
                                id bigint NOT NULL,
                                name text NOT NULL,
                                price integer NOT NULL
);


ALTER TABLE public.product OWNER TO postgres;

ALTER TABLE public.product ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.product_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
    );

CREATE TABLE public.purchase (
                                 id bigint NOT NULL,
                                 customer_id bigint NOT NULL,
                                 product_id bigint NOT NULL,
                                 date date NOT NULL
);


ALTER TABLE public.purchase OWNER TO postgres;

ALTER TABLE public.purchase ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.purchase_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
    );


COPY public.customer (id, first_name, last_name) FROM stdin;
1       Andrew  Korn
2       Nastya  Korn
3       Vasya   Pupkin
4       Petr    James
5       Valera  Pupkin
6       Kostya  Drew
7       Dasha   West
8       Masha   Ra
9       Igor    Pupkin
10      Max     Little
11      Kristina        Forest
\.


COPY public.product (id, name, price) FROM stdin;
1       Milk    80
2       Bread   50
3       Water   30
4       Chocolate       100
5       Rice    50
6       Sugar   70
7       Cola    150
8       Chicken 200
9       Cheese  120
10      Beer    50
\.

COPY public.purchase (id, customer_id, product_id, date) FROM stdin;
2       1       1       2022-11-17
3       1       2       2022-11-17
4       2       3       2022-11-17
5       2       3       2022-11-18
6       1       10      2022-11-18
7       3       1       2022-11-18
8       4       5       2022-11-15
9       1       10      2022-11-11
10      5       4       2022-11-12
11      1       10      2022-11-04
12      6       8       2022-11-13
13      8       6       2022-11-17
14      9       7       2022-11-01
\.


SELECT pg_catalog.setval('public.customer_id_seq', 11, true);


SELECT pg_catalog.setval('public.product_id_seq', 10, true);


SELECT pg_catalog.setval('public.purchase_id_seq', 14, true);


ALTER TABLE ONLY public.product
    ADD CONSTRAINT "Product_pk" PRIMARY KEY (id);

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_pk PRIMARY KEY (id);


ALTER TABLE ONLY public.purchase
    ADD CONSTRAINT purchase_pk PRIMARY KEY (id);


ALTER TABLE ONLY public.purchase
    ADD CONSTRAINT purchase_customer_fk FOREIGN KEY (customer_id) REFERENCES public.customer(id);


ALTER TABLE ONLY public.purchase
    ADD CONSTRAINT purchase_product_fk FOREIGN KEY (product_id) REFERENCES public.product(id);

