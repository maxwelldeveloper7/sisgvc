--
-- PostgreSQL database dump
--

-- Dumped from database version 12.12 (Ubuntu 12.12-1.pgdg20.04+1)
-- Dumped by pg_dump version 14.5 (Ubuntu 14.5-1.pgdg20.04+1)

-- Started on 2022-10-17 21:27:47 -03

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

--
-- TOC entry 253 (class 1255 OID 16387)
-- Name: altera_brinde_pedido(integer, integer, integer, numeric, integer, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.altera_brinde_pedido(integer, integer, integer, numeric, integer, integer) RETURNS void
    LANGUAGE plpgsql
    AS $_$
DECLARE

    cod_produto integer:= $1;
    quantidade integer:= $2;
    cod_pedido integer:= $3;
    valor numeric:= $4;
    tipo integer:= $5;
    codigo integer:= $6;
    
    qt_old integer:= (select qt_brinde from brindes_pedido where id = codigo);
    cod_prod_old integer:= (select cd_produto from brindes_pedido where id = codigo);   
    

BEGIN
    UPDATE brindes_pedido SET cd_produto=cod_produto, qt_brinde=quantidade, cd_pedido=cod_pedido, vl_brinde=valor, tp_brinde = tipo WHERE id=codigo;

    IF cod_produto = cod_prod_old THEN
        PERFORM (SELECT atualiza_estoque(1, cod_produto, quantidade, qt_old));
    ELSE
        PERFORM (SELECT atualiza_estoque(1, cod_prod_old, 0, qt_old));
        PERFORM (SELECT atualiza_estoque(0, cod_produto, quantidade, 0));
    END IF;

END
$_$;


ALTER FUNCTION public.altera_brinde_pedido(integer, integer, integer, numeric, integer, integer) OWNER TO postgres;

--
-- TOC entry 254 (class 1255 OID 16388)
-- Name: altera_item_compra(integer, integer, integer, numeric, bigint); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.altera_item_compra(integer, integer, integer, numeric, bigint) RETURNS void
    LANGUAGE plpgsql
    AS $_$
DECLARE

    cod_compra integer:= $1;
    cod_produto integer:= $2;
    qt integer:= $3;
    valor numeric:= $4;
    codigo bigint:= $5;
   
    qt_old integer:= (select qt_produto from itens_compra where id = codigo);
    cod_prod_old integer:= (select cd_produto from itens_compra where id = codigo); 
    

BEGIN
    UPDATE itens_compra SET cd_compra=cod_compra, cd_produto=cod_produto, qt_produto = qt, vl_unitario=valor WHERE id=codigo;

    
    IF cod_produto = cod_prod_old THEN
        PERFORM (SELECT atualiza_estoque(4, cod_produto, qt, qt_old));
    ELSE
        PERFORM (SELECT atualiza_estoque(4, cod_prod_old, 0, qt_old));
        PERFORM (SELECT atualiza_estoque(3, cod_produto, qt, 0));
    END IF;

END
$_$;


ALTER FUNCTION public.altera_item_compra(integer, integer, integer, numeric, bigint) OWNER TO postgres;

--
-- TOC entry 255 (class 1255 OID 16389)
-- Name: altera_item_pedido(integer, integer, integer, integer, integer, numeric, numeric, bigint); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.altera_item_pedido(integer, integer, integer, integer, integer, numeric, numeric, bigint) RETURNS void
    LANGUAGE plpgsql
    AS $_$
DECLARE

    cod_produto integer:= $1;
    consig integer:= $2;
    vend integer:= $3;
    dev integer:= $4;
    cod_pedido integer:= $5;
    valor_v numeric:= $6;
    valor_c numeric:= $7;
    codigo bigint:= $8;
    
    consig_old integer:= (select consignado from itens_pedido where id = codigo);
    vend_old integer:= (select vendido from itens_pedido where id = codigo);
    cod_prod_old integer:= (select cd_produto from itens_pedido where id = codigo); 
    

BEGIN
    UPDATE itens_pedido SET cd_produto=cod_produto, consignado=consig, vendido=vend, devolvido=dev, cd_pedido=cod_pedido, vl_unitario=valor_v, vl_custo=valor_c WHERE id=codigo;

    --PERFORM (SELECT atualiza_estoque(1, cod_produto, (consig + vend),(consig_old + vend_old)));

    IF cod_produto = cod_prod_old THEN
        PERFORM (SELECT atualiza_estoque(1, cod_produto, (consig + vend), (consig_old + vend_old)));
    ELSE
        PERFORM (SELECT atualiza_estoque(1, cod_prod_old, 0, (consig_old + vend_old)));
        PERFORM (SELECT atualiza_estoque(0, cod_produto, (consig + vend), 0));
    END IF;

END
$_$;


ALTER FUNCTION public.altera_item_pedido(integer, integer, integer, integer, integer, numeric, numeric, bigint) OWNER TO postgres;

--
-- TOC entry 269 (class 1255 OID 16390)
-- Name: atualiza_estoque(integer, integer, integer, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.atualiza_estoque(integer, integer, integer, integer) RETURNS void
    LANGUAGE plpgsql
    AS $_$
DECLARE

    operacao integer:= $1; -- insert/saída 0, update/saída 1, delete/saída 2, insert/entrada 3, update/entrada 4, delete/entrada 5
    cod_produto integer:= $2; -- código do produto
    qt_new integer:= $3;
    qt_old integer:= $4;

    estoque_old integer:= (SELECT estoque FROM produtos WHERE id = cod_produto);
    id_max bigint:= (Select max(id) from pedidos);

BEGIN

    IF operacao = 0 THEN
        UPDATE produtos SET estoque = (estoque_old - qt_new) WHERE id = cod_produto;
    END IF;

    IF operacao = 1 THEN
        UPDATE produtos SET estoque = (estoque_old + qt_old - qt_new) WHERE id = cod_produto;
    END IF;

    IF operacao = 2 THEN
        UPDATE produtos SET estoque = (estoque_old + qt_old) WHERE id = cod_produto;
    END IF;

    IF operacao = 3 THEN
        UPDATE produtos SET estoque = (estoque_old + qt_new) WHERE id = cod_produto;
    END IF;

    IF operacao = 4 THEN
        UPDATE produtos SET estoque = (estoque_old - qt_old + qt_new) WHERE id = cod_produto;
    END IF;
    
    IF operacao = 5 THEN
        UPDATE produtos SET estoque = (estoque_old - qt_old) WHERE id = cod_produto;
    END IF;

END
$_$;


ALTER FUNCTION public.atualiza_estoque(integer, integer, integer, integer) OWNER TO postgres;

--
-- TOC entry 270 (class 1255 OID 16391)
-- Name: insere_brinde_pedido(integer, integer, integer, numeric, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.insere_brinde_pedido(integer, integer, integer, numeric, integer) RETURNS void
    LANGUAGE plpgsql
    AS $_$
DECLARE
    cod_produto integer:= $1;
    quantidade integer:= $2;
    cod_pedido integer:= $3;
    valor numeric:= $4;
    tipo integer:= $5;

    

BEGIN
    INSERT INTO brindes_pedido (cd_produto, qt_brinde, cd_pedido, vl_brinde, tp_brinde) values (cod_produto, quantidade, cod_pedido, valor, tipo);

    PERFORM (SELECT atualiza_estoque(0, cod_produto, (quantidade),0));

END
$_$;


ALTER FUNCTION public.insere_brinde_pedido(integer, integer, integer, numeric, integer) OWNER TO postgres;

--
-- TOC entry 271 (class 1255 OID 16392)
-- Name: insere_item_compra(integer, integer, integer, numeric); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.insere_item_compra(integer, integer, integer, numeric) RETURNS void
    LANGUAGE plpgsql
    AS $_$
DECLARE
    cod_compra integer:= $1;
    cod_produto integer:= $2;
    qt integer:= $3;
    valor numeric:= $4;    

    

BEGIN
    INSERT INTO itens_compra (cd_compra, cd_produto, qt_produto, vl_unitario) values (cod_compra, cod_produto, qt, valor);

    PERFORM (SELECT atualiza_estoque(3, cod_produto, qt,0));

END
$_$;


ALTER FUNCTION public.insere_item_compra(integer, integer, integer, numeric) OWNER TO postgres;

--
-- TOC entry 272 (class 1255 OID 16393)
-- Name: insere_item_pedido(integer, integer, integer, integer, integer, numeric, numeric); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.insere_item_pedido(integer, integer, integer, integer, integer, numeric, numeric) RETURNS void
    LANGUAGE plpgsql
    AS $_$
DECLARE
    cod_produto integer:= $1;
    consig integer:= $2;
    vend integer:= $3;
    dev integer:= $4;
    cod_pedido integer:= $5;
    valor_v numeric:= $6;
    valor_c numeric:= $7;

    

BEGIN
    INSERT INTO itens_pedido (cd_produto, consignado, vendido, devolvido, cd_pedido, vl_unitario, vl_custo) values (cod_produto, consig, vend, dev, cod_pedido, valor_v, valor_c);

    PERFORM (SELECT atualiza_estoque(0, cod_produto, (consig + vend),0));

END
$_$;


ALTER FUNCTION public.insere_item_pedido(integer, integer, integer, integer, integer, numeric, numeric) OWNER TO postgres;

--
-- TOC entry 273 (class 1255 OID 16394)
-- Name: insere_perfil(character varying); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.insere_perfil(character varying) RETURNS void
    LANGUAGE plpgsql
    AS $_$
declare
    nr_rotinas Integer:= (select count(*) from rotinas);--número de registros da tabela de rotinas.
    id_perfil Integer;--armazenará o id do perfil recuperado por um select logo após sua inserção.
    i Integer:=1;--contador do loop
    
begin
    INSERT INTO perfis (no_perfil) VALUES ($1);--insere o perfil.
    id_perfil:= (select id from perfis where no_perfil = $1);--recupera o id do perfil inserido.

    for i in 1..nr_rotinas loop
        INSERT INTO privilegios (cd_rotina,cd_perfil) VALUES (i,id_perfil);
    end loop;
end;

$_$;


ALTER FUNCTION public.insere_perfil(character varying) OWNER TO postgres;

--
-- TOC entry 274 (class 1255 OID 16395)
-- Name: insere_perfil(character varying, character varying); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.insere_perfil(character varying, character varying) RETURNS void
    LANGUAGE plpgsql
    AS $_$
declare
    nr_rotinas Integer:= (select count(*) from rotinas);--número de registros da tabela de rotinas.
    id_perfil Integer;--armazenará o id do perfil recuperado por um select logo após sua inserção.
    i Integer:=1;--contador do loop
    
begin
    INSERT INTO perfis (no_perfil, ds_perfil) VALUES ($1, $2);--insere o perfil.
    id_perfil:= (select id from perfis where no_perfil = $1);--recupera o id do perfil inserido.

    for i in 1..nr_rotinas loop
        INSERT INTO privilegios (cd_rotina,cd_perfil) VALUES (i,id_perfil);
    end loop;
end;

$_$;


ALTER FUNCTION public.insere_perfil(character varying, character varying) OWNER TO postgres;

--
-- TOC entry 275 (class 1255 OID 16396)
-- Name: remove_brinde_pedido(integer, integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.remove_brinde_pedido(integer, integer) RETURNS void
    LANGUAGE plpgsql
    AS $_$
DECLARE

    cod_produto integer:= $1;
    codigo integer:= $2;
    
    qt_old integer:= (select qt_brinde from brindes_pedido where id = codigo);
    
    

BEGIN
    DELETE FROM brindes_pedido  WHERE id = codigo;

    PERFORM (SELECT atualiza_estoque(2, cod_produto, 0,qt_old));

END
$_$;


ALTER FUNCTION public.remove_brinde_pedido(integer, integer) OWNER TO postgres;

--
-- TOC entry 276 (class 1255 OID 16397)
-- Name: remove_compra(integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.remove_compra(integer) RETURNS void
    LANGUAGE plpgsql
    AS $_$
DECLARE

cod_compra integer:= $1;
nr_itens integer:= (SELECT COUNT(id) FROM itens_compra WHERE cd_compra = cod_compra);

item bigint:=0;
produto integer:= 0;

BEGIN
    FOR i IN 1..nr_itens LOOP
        item = (SELECT MIN(id) FROM itens_compra WHERE cd_compra = cd_compra);
	produto = (SELECT cd_produto FROM itens_compra WHERE id = item);
	PERFORM (SELECT remove_item_compra(produto, item));
    END LOOP;

    DELETE FROM compras WHERE id = cod_compra;

END
$_$;


ALTER FUNCTION public.remove_compra(integer) OWNER TO postgres;

--
-- TOC entry 277 (class 1255 OID 16398)
-- Name: remove_item_compra(integer, bigint); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.remove_item_compra(integer, bigint) RETURNS void
    LANGUAGE plpgsql
    AS $_$
DECLARE

    cod_produto integer:= $1;
    codigo bigint:= $2;
    
    qt_old integer:= (select qt_produto from itens_compra where id = codigo);
    

BEGIN
    DELETE FROM itens_compra  WHERE id = codigo;

    PERFORM (SELECT atualiza_estoque(5, cod_produto, 0, qt_old));

END
$_$;


ALTER FUNCTION public.remove_item_compra(integer, bigint) OWNER TO postgres;

--
-- TOC entry 278 (class 1255 OID 16399)
-- Name: remove_item_pedido(integer, bigint); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.remove_item_pedido(integer, bigint) RETURNS void
    LANGUAGE plpgsql
    AS $_$
DECLARE

    cod_produto integer:= $1;
    codigo bigint:= $2;
    
    consig_old integer:= (select consignado from itens_pedido where id = codigo);
    vend_old integer:= (select vendido from itens_pedido where id = codigo); 
    

BEGIN
    DELETE FROM itens_pedido  WHERE id = codigo;

    PERFORM (SELECT atualiza_estoque(2, cod_produto, 0,(consig_old + vend_old)));

END
$_$;


ALTER FUNCTION public.remove_item_pedido(integer, bigint) OWNER TO postgres;

--
-- TOC entry 256 (class 1255 OID 16400)
-- Name: remove_pedido(integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.remove_pedido(integer) RETURNS void
    LANGUAGE plpgsql
    AS $_$
DECLARE

cod_pedido integer:= $1;
nr_itens integer:= (SELECT COUNT(id) FROM itens_pedido WHERE cd_pedido = cod_pedido);
nr_brindes integer:= (SELECT COUNT(id) FROM brindes_pedido WHERE cd_pedido = cod_pedido);

item bigint:=0;
brinde integer:= 0;
produto integer:= 0;

BEGIN
    FOR i IN 1..nr_itens LOOP
        item = (SELECT MIN(id) FROM itens_pedido WHERE cd_pedido = cod_pedido);
	produto = (SELECT cd_produto FROM itens_pedido WHERE id = item);
	PERFORM (SELECT remove_item_pedido(produto, item));
    END LOOP;

    produto := 0;

    FOR i IN 1..nr_brindes LOOP
        brinde = (SELECT MIN(id) FROM brindes_pedido WHERE cd_pedido = cod_pedido);
	produto = (SELECT cd_produto FROM brindes_pedido WHERE id = brinde);
	PERFORM (SELECT remove_brinde_pedido(produto, brinde));
    END LOOP;

    DELETE FROM pedidos WHERE id = cod_pedido;    	


END
$_$;


ALTER FUNCTION public.remove_pedido(integer) OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 202 (class 1259 OID 16401)
-- Name: brindes_pedido; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.brindes_pedido (
    id integer NOT NULL,
    cd_produto integer NOT NULL,
    qt_brinde integer DEFAULT 1 NOT NULL,
    cd_pedido integer NOT NULL,
    vl_brinde numeric(10,2) DEFAULT 0.00 NOT NULL,
    tp_brinde integer
);


ALTER TABLE public.brindes_pedido OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 16406)
-- Name: brindes_venda_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.brindes_venda_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.brindes_venda_id_seq OWNER TO postgres;

--
-- TOC entry 3255 (class 0 OID 0)
-- Dependencies: 203
-- Name: brindes_venda_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.brindes_venda_id_seq OWNED BY public.brindes_pedido.id;


--
-- TOC entry 204 (class 1259 OID 16408)
-- Name: cidades; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cidades (
    id integer NOT NULL,
    no_cidade character varying(50),
    uf character(2)
);


ALTER TABLE public.cidades OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 16411)
-- Name: cidades_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cidades_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cidades_id_seq OWNER TO postgres;

--
-- TOC entry 3256 (class 0 OID 0)
-- Dependencies: 205
-- Name: cidades_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cidades_id_seq OWNED BY public.cidades.id;


--
-- TOC entry 206 (class 1259 OID 16413)
-- Name: clientes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.clientes (
    id integer NOT NULL,
    no_cliente character varying(50) NOT NULL,
    rg_cliente character varying(50),
    cpf_cliente character varying(11),
    dt_nascimento date,
    lged_cliente character varying(100),
    cep_cliente character varying(8),
    tl_cliente character varying(10),
    cl_cliente character varying(11),
    nred_cliente character varying(6),
    bred_cliente character varying(50),
    cd_cidade integer,
    corc_cliente character varying(20),
    nrf_cliente character varying(10),
    nrl_cliente character varying(10),
    pr_cliente character varying(150),
    ape_cliente character varying(20)
);


ALTER TABLE public.clientes OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 16419)
-- Name: clientes_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.clientes_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.clientes_id_seq OWNER TO postgres;

--
-- TOC entry 3257 (class 0 OID 0)
-- Dependencies: 207
-- Name: clientes_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.clientes_id_seq OWNED BY public.clientes.id;


--
-- TOC entry 208 (class 1259 OID 16421)
-- Name: compras; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.compras (
    id integer NOT NULL,
    nr_nt_fiscal character varying(20) NOT NULL,
    cd_fornecedor integer NOT NULL,
    dt_emissao date,
    dt_entrega date
);


ALTER TABLE public.compras OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 16424)
-- Name: compras_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.compras_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.compras_id_seq OWNER TO postgres;

--
-- TOC entry 3258 (class 0 OID 0)
-- Dependencies: 209
-- Name: compras_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.compras_id_seq OWNED BY public.compras.id;


--
-- TOC entry 210 (class 1259 OID 16426)
-- Name: configuracoes_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.configuracoes_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.configuracoes_id_seq OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 16428)
-- Name: configuracoes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.configuracoes (
    id integer DEFAULT nextval('public.configuracoes_id_seq'::regclass) NOT NULL,
    no_licenciada character varying(100),
    cnpj_licenciada character varying(14),
    dt_vencimento date,
    ac_liberado boolean,
    dt_bloqueio date
);


ALTER TABLE public.configuracoes OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 16432)
-- Name: equipes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.equipes (
    id integer NOT NULL,
    no_equipe character varying NOT NULL
);


ALTER TABLE public.equipes OWNER TO postgres;

--
-- TOC entry 3259 (class 0 OID 0)
-- Dependencies: 212
-- Name: COLUMN equipes.no_equipe; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.equipes.no_equipe IS 'Nome da equipe';


--
-- TOC entry 213 (class 1259 OID 16438)
-- Name: equipes_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.equipes_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.equipes_id_seq OWNER TO postgres;

--
-- TOC entry 3260 (class 0 OID 0)
-- Dependencies: 213
-- Name: equipes_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.equipes_id_seq OWNED BY public.equipes.id;


--
-- TOC entry 214 (class 1259 OID 16440)
-- Name: fornecedores; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.fornecedores (
    id integer NOT NULL,
    no_fornecedor character varying(50) NOT NULL,
    cnpj character varying(14),
    ie character varying(12),
    ed_fornecedor character varying(100),
    uf_fornecedor character(2),
    cid_fornecedor character varying(20),
    cep_fornecedor character varying(8),
    tl_fornecedor character varying(10)
);


ALTER TABLE public.fornecedores OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 16443)
-- Name: fornecedores_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.fornecedores_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.fornecedores_id_seq OWNER TO postgres;

--
-- TOC entry 3261 (class 0 OID 0)
-- Dependencies: 215
-- Name: fornecedores_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.fornecedores_id_seq OWNED BY public.fornecedores.id;


--
-- TOC entry 216 (class 1259 OID 16445)
-- Name: funcionarios; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.funcionarios (
    id integer NOT NULL,
    no_funcionario character varying(50) NOT NULL,
    rg_funcionario character varying(20),
    cpf_funcionario character varying(11),
    ed_funcionario character varying(100),
    uf_funcionario character(2),
    cid_funcionario character varying(50),
    dt_admissao date,
    dt_demissao date,
    cep_funcionario character varying(8),
    tl_funcionario character varying(10),
    nr_cc character varying(20),
    nr_agencia character varying(20),
    no_banco character varying(50)
);


ALTER TABLE public.funcionarios OWNER TO postgres;

--
-- TOC entry 3262 (class 0 OID 0)
-- Dependencies: 216
-- Name: COLUMN funcionarios.no_funcionario; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.funcionarios.no_funcionario IS 'Nome do funcionário';


--
-- TOC entry 3263 (class 0 OID 0)
-- Dependencies: 216
-- Name: COLUMN funcionarios.rg_funcionario; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.funcionarios.rg_funcionario IS 'RG';


--
-- TOC entry 3264 (class 0 OID 0)
-- Dependencies: 216
-- Name: COLUMN funcionarios.cpf_funcionario; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.funcionarios.cpf_funcionario IS 'CPF';


--
-- TOC entry 3265 (class 0 OID 0)
-- Dependencies: 216
-- Name: COLUMN funcionarios.ed_funcionario; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.funcionarios.ed_funcionario IS 'Endereço do funcionário';


--
-- TOC entry 3266 (class 0 OID 0)
-- Dependencies: 216
-- Name: COLUMN funcionarios.cid_funcionario; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.funcionarios.cid_funcionario IS 'Cidade do funcionário';


--
-- TOC entry 3267 (class 0 OID 0)
-- Dependencies: 216
-- Name: COLUMN funcionarios.tl_funcionario; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.funcionarios.tl_funcionario IS 'Telefone do funcionário';


--
-- TOC entry 3268 (class 0 OID 0)
-- Dependencies: 216
-- Name: COLUMN funcionarios.nr_cc; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.funcionarios.nr_cc IS 'Número da conta corrente';


--
-- TOC entry 3269 (class 0 OID 0)
-- Dependencies: 216
-- Name: COLUMN funcionarios.nr_agencia; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.funcionarios.nr_agencia IS 'Número da Agência';


--
-- TOC entry 3270 (class 0 OID 0)
-- Dependencies: 216
-- Name: COLUMN funcionarios.no_banco; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.funcionarios.no_banco IS 'Nome do Banco';


--
-- TOC entry 217 (class 1259 OID 16448)
-- Name: funcionarios_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.funcionarios_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.funcionarios_id_seq OWNER TO postgres;

--
-- TOC entry 3271 (class 0 OID 0)
-- Dependencies: 217
-- Name: funcionarios_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.funcionarios_id_seq OWNED BY public.funcionarios.id;


--
-- TOC entry 218 (class 1259 OID 16450)
-- Name: itens_compra; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.itens_compra (
    id bigint NOT NULL,
    cd_compra integer NOT NULL,
    cd_produto integer NOT NULL,
    qt_produto integer DEFAULT 1 NOT NULL,
    vl_unitario numeric(10,2) DEFAULT 0.00 NOT NULL
);


ALTER TABLE public.itens_compra OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 16455)
-- Name: itens_compra_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.itens_compra_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.itens_compra_id_seq OWNER TO postgres;

--
-- TOC entry 3272 (class 0 OID 0)
-- Dependencies: 219
-- Name: itens_compra_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.itens_compra_id_seq OWNED BY public.itens_compra.id;


--
-- TOC entry 220 (class 1259 OID 16457)
-- Name: itens_pedido; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.itens_pedido (
    id bigint NOT NULL,
    cd_produto integer NOT NULL,
    consignado integer NOT NULL,
    vendido integer DEFAULT 0 NOT NULL,
    devolvido integer DEFAULT 0 NOT NULL,
    cd_pedido integer NOT NULL,
    vl_unitario numeric(10,2) DEFAULT 0.00 NOT NULL,
    vl_custo numeric(10,2) DEFAULT 0.00 NOT NULL
);


ALTER TABLE public.itens_pedido OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 16464)
-- Name: itens_venda_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.itens_venda_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.itens_venda_id_seq OWNER TO postgres;

--
-- TOC entry 3273 (class 0 OID 0)
-- Dependencies: 221
-- Name: itens_venda_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.itens_venda_id_seq OWNED BY public.itens_pedido.id;


--
-- TOC entry 222 (class 1259 OID 16466)
-- Name: medidas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.medidas (
    id integer NOT NULL,
    no_medida character varying NOT NULL
);


ALTER TABLE public.medidas OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 16472)
-- Name: medidas_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.medidas_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.medidas_id_seq OWNER TO postgres;

--
-- TOC entry 3274 (class 0 OID 0)
-- Dependencies: 223
-- Name: medidas_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.medidas_id_seq OWNED BY public.medidas.id;


--
-- TOC entry 224 (class 1259 OID 16474)
-- Name: rotinas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.rotinas (
    id integer NOT NULL,
    no_rotina character varying(50) NOT NULL
);


ALTER TABLE public.rotinas OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 16477)
-- Name: modulos_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.modulos_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.modulos_id_seq OWNER TO postgres;

--
-- TOC entry 3275 (class 0 OID 0)
-- Dependencies: 225
-- Name: modulos_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.modulos_id_seq OWNED BY public.rotinas.id;


--
-- TOC entry 226 (class 1259 OID 16479)
-- Name: motivos_perdas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.motivos_perdas (
    id integer NOT NULL,
    mt_perda character varying(50)
);


ALTER TABLE public.motivos_perdas OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 16482)
-- Name: motivos_perdas_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.motivos_perdas_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.motivos_perdas_id_seq OWNER TO postgres;

--
-- TOC entry 3276 (class 0 OID 0)
-- Dependencies: 227
-- Name: motivos_perdas_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.motivos_perdas_id_seq OWNED BY public.motivos_perdas.id;


--
-- TOC entry 228 (class 1259 OID 16484)
-- Name: pedidos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pedidos (
    id integer NOT NULL,
    cd_equipe integer NOT NULL,
    cd_cliente integer NOT NULL,
    dt_entrega date NOT NULL,
    dt_vencimento date NOT NULL,
    cd_status integer NOT NULL,
    cd_vendedor integer NOT NULL,
    vl_desconto numeric(10,2) DEFAULT 0.00,
    vl_pedido numeric(10,2) DEFAULT 0.00,
    vl_recebido numeric(10,2) DEFAULT 0.00,
    vl_brinde numeric(10,2) DEFAULT 0.00,
    vlp01 numeric(10,2),
    vlp02 numeric(10,2),
    vlp03 numeric(10,2),
    vlp04 numeric(10,2),
    vlp05 numeric(10,2),
    vlp06 numeric(10,2),
    vlp07 numeric(10,2),
    vlp08 numeric(10,2),
    vlp09 numeric(10,2),
    dtp01 date,
    dtp02 date,
    dtp03 date,
    dtp04 date,
    dtp05 date,
    dtp06 date,
    dtp07 date,
    dtp08 date,
    dtp09 date,
    vlp10 numeric(10,2),
    vlp11 numeric(10,2),
    vlp12 numeric(10,2),
    vlp13 numeric(10,2),
    vlp14 numeric(10,2),
    vlp15 numeric(10,2),
    dtp10 date,
    dtp11 date,
    dtp12 date,
    dtp13 date,
    dtp14 date,
    dtp15 date,
    cd_mtperda integer DEFAULT 1
);


ALTER TABLE public.pedidos OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 16492)
-- Name: pedidos_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.pedidos_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
    CYCLE;


ALTER TABLE public.pedidos_id_seq OWNER TO postgres;

--
-- TOC entry 3277 (class 0 OID 0)
-- Dependencies: 229
-- Name: pedidos_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.pedidos_id_seq OWNED BY public.pedidos.id;


--
-- TOC entry 230 (class 1259 OID 16494)
-- Name: perfis; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.perfis (
    id integer NOT NULL,
    no_perfil character varying(50) NOT NULL,
    ds_perfil character varying(255)
);


ALTER TABLE public.perfis OWNER TO postgres;

--
-- TOC entry 231 (class 1259 OID 16497)
-- Name: perfis_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.perfis_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.perfis_id_seq OWNER TO postgres;

--
-- TOC entry 3278 (class 0 OID 0)
-- Dependencies: 231
-- Name: perfis_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.perfis_id_seq OWNED BY public.perfis.id;


--
-- TOC entry 232 (class 1259 OID 16499)
-- Name: privilegios; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.privilegios (
    id integer NOT NULL,
    cd_rotina integer NOT NULL,
    acessar boolean DEFAULT false NOT NULL,
    adicionar boolean DEFAULT true NOT NULL,
    editar boolean DEFAULT true NOT NULL,
    excluir boolean DEFAULT true NOT NULL,
    cd_perfil integer NOT NULL
);


ALTER TABLE public.privilegios OWNER TO postgres;

--
-- TOC entry 233 (class 1259 OID 16506)
-- Name: privilegios_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.privilegios_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.privilegios_id_seq OWNER TO postgres;

--
-- TOC entry 3279 (class 0 OID 0)
-- Dependencies: 233
-- Name: privilegios_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.privilegios_id_seq OWNED BY public.privilegios.id;


--
-- TOC entry 234 (class 1259 OID 16508)
-- Name: produtos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.produtos (
    id integer NOT NULL,
    no_produto character varying(100) NOT NULL,
    cd_medida integer NOT NULL,
    vl_compra numeric(10,2) DEFAULT 0.00,
    vl_unitario numeric(10,2) DEFAULT 0.00,
    estoque integer DEFAULT 0 NOT NULL,
    tp_produto integer
);


ALTER TABLE public.produtos OWNER TO postgres;

--
-- TOC entry 235 (class 1259 OID 16514)
-- Name: produtos_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.produtos_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.produtos_id_seq OWNER TO postgres;

--
-- TOC entry 3280 (class 0 OID 0)
-- Dependencies: 235
-- Name: produtos_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.produtos_id_seq OWNED BY public.produtos.id;


--
-- TOC entry 236 (class 1259 OID 16516)
-- Name: status_vendas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.status_vendas (
    id integer NOT NULL,
    st_venda character varying(20) NOT NULL
);


ALTER TABLE public.status_vendas OWNER TO postgres;

--
-- TOC entry 237 (class 1259 OID 16519)
-- Name: status_vendas_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.status_vendas_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.status_vendas_id_seq OWNER TO postgres;

--
-- TOC entry 3281 (class 0 OID 0)
-- Dependencies: 237
-- Name: status_vendas_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.status_vendas_id_seq OWNED BY public.status_vendas.id;


--
-- TOC entry 238 (class 1259 OID 16521)
-- Name: usuarios; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuarios (
    id integer NOT NULL,
    no_usuario character varying(50) NOT NULL,
    login character varying(50) NOT NULL,
    senha character varying(16) NOT NULL,
    cd_perfil integer NOT NULL,
    ativo boolean DEFAULT true NOT NULL
);


ALTER TABLE public.usuarios OWNER TO postgres;

--
-- TOC entry 239 (class 1259 OID 16525)
-- Name: usuarios_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.usuarios_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usuarios_id_seq OWNER TO postgres;

--
-- TOC entry 3282 (class 0 OID 0)
-- Dependencies: 239
-- Name: usuarios_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.usuarios_id_seq OWNED BY public.usuarios.id;


--
-- TOC entry 240 (class 1259 OID 16527)
-- Name: vw_brindes_pedido; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.vw_brindes_pedido AS
 SELECT b.id,
    b.cd_produto,
    p.no_produto,
    p.cd_medida,
    m.no_medida,
    p.vl_compra AS vl_custo,
    p.vl_unitario AS vl_venda,
    b.qt_brinde,
    b.cd_pedido,
    b.vl_brinde,
    (b.vl_brinde * (b.qt_brinde)::numeric) AS vl_total,
    b.tp_brinde
   FROM ((public.medidas m
     JOIN public.produtos p ON ((m.id = p.cd_medida)))
     JOIN public.brindes_pedido b ON ((p.id = b.cd_produto)))
  ORDER BY b.id;


ALTER TABLE public.vw_brindes_pedido OWNER TO postgres;

--
-- TOC entry 241 (class 1259 OID 16532)
-- Name: vw_cliente_status_venda; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.vw_cliente_status_venda AS
 SELECT v.cd_cliente,
    c.no_cliente,
    c.lged_cliente,
    c.nred_cliente,
    c.bred_cliente,
    cid.uf,
    cid.no_cidade,
    s.st_venda,
    ( SELECT count(s.st_venda) AS count
          WHERE ((s.st_venda)::text = 'CONSIGNADO'::text)) AS consignado,
    ( SELECT count(s.st_venda) AS count
          WHERE ((s.st_venda)::text = 'PENDENTE'::text)) AS pendente,
    ( SELECT count(s.st_venda) AS count
          WHERE ((s.st_venda)::text = 'DEVOLVIDO'::text)) AS devolvido,
    ( SELECT count(s.st_venda) AS count
          WHERE ((s.st_venda)::text = 'CONCLUÍDO'::text)) AS concluido,
    ( SELECT count(s.st_venda) AS count
          WHERE ((s.st_venda)::text = 'PERDIDO'::text)) AS perdido
   FROM (((public.pedidos v
     JOIN public.clientes c ON ((v.cd_cliente = c.id)))
     JOIN public.cidades cid ON ((c.cd_cidade = cid.id)))
     JOIN public.status_vendas s ON ((v.cd_status = s.id)))
  GROUP BY v.cd_cliente, c.no_cliente, s.st_venda, c.lged_cliente, c.nred_cliente, c.bred_cliente, cid.uf, cid.no_cidade;


ALTER TABLE public.vw_cliente_status_venda OWNER TO postgres;

--
-- TOC entry 242 (class 1259 OID 16537)
-- Name: vw_clientes; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.vw_clientes AS
 SELECT cli.id,
    cli.no_cliente AS nome,
    cli.rg_cliente AS rg,
    cli.cpf_cliente AS cpf,
    cli.dt_nascimento AS nascimento,
    cli.lged_cliente AS logradouro,
    cli.nred_cliente AS numero,
    cli.bred_cliente AS bairro,
    cli.cep_cliente AS cep,
    cid.no_cidade AS cidade,
    cid.uf,
    cli.tl_cliente AS telefone,
    cli.cl_cliente AS celular,
    cli.corc_cliente,
    cli.nrf_cliente,
    cli.nrl_cliente,
    cli.pr_cliente,
    cli.ape_cliente
   FROM (public.clientes cli
     JOIN public.cidades cid ON ((cli.cd_cidade = cid.id)))
  ORDER BY cli.no_cliente;


ALTER TABLE public.vw_clientes OWNER TO postgres;

--
-- TOC entry 243 (class 1259 OID 16542)
-- Name: vw_compras; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.vw_compras AS
 SELECT c.id,
    c.nr_nt_fiscal,
    c.cd_fornecedor,
    f.no_fornecedor,
    f.cnpj,
    f.ie,
    f.ed_fornecedor,
    f.uf_fornecedor,
    f.cid_fornecedor,
    f.cep_fornecedor,
    f.tl_fornecedor,
    c.dt_emissao,
    c.dt_entrega
   FROM (public.fornecedores f
     JOIN public.compras c ON ((f.id = c.cd_fornecedor)));


ALTER TABLE public.vw_compras OWNER TO postgres;

--
-- TOC entry 244 (class 1259 OID 16546)
-- Name: vw_consolidado; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.vw_consolidado AS
 SELECT vw_cliente_status_venda.cd_cliente AS codigo,
    vw_cliente_status_venda.no_cliente AS cliente,
    vw_cliente_status_venda.lged_cliente AS endereco,
    vw_cliente_status_venda.nred_cliente AS numero,
    vw_cliente_status_venda.bred_cliente AS bairro,
    vw_cliente_status_venda.uf,
    vw_cliente_status_venda.no_cidade AS cidade,
    sum(vw_cliente_status_venda.consignado) AS consignados,
    sum(vw_cliente_status_venda.pendente) AS pendentes,
    sum(vw_cliente_status_venda.devolvido) AS devolvidos,
    sum(vw_cliente_status_venda.concluido) AS concluidos,
    sum(vw_cliente_status_venda.perdido) AS perdidos
   FROM public.vw_cliente_status_venda
  GROUP BY vw_cliente_status_venda.cd_cliente, vw_cliente_status_venda.no_cliente, vw_cliente_status_venda.lged_cliente, vw_cliente_status_venda.nred_cliente, vw_cliente_status_venda.bred_cliente, vw_cliente_status_venda.uf, vw_cliente_status_venda.no_cidade;


ALTER TABLE public.vw_consolidado OWNER TO postgres;

--
-- TOC entry 245 (class 1259 OID 16550)
-- Name: vw_devolvidos_pendentes; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.vw_devolvidos_pendentes AS
 SELECT sum(iv.consignado) AS consignados,
    sum(iv.vendido) AS vendidos,
    sum(iv.devolvido) AS devolvidos,
    sum((iv.vl_unitario * (iv.consignado)::numeric)) AS vl_consignado,
    sum((iv.vl_unitario * (iv.vendido)::numeric)) AS vl_vendido,
    sum((iv.vl_unitario * (iv.devolvido)::numeric)) AS vl_devolvido,
    iv.cd_pedido AS cd_venda,
    v.cd_status,
    v.vl_pedido,
    v.vl_recebido,
    v.vl_desconto
   FROM (public.itens_pedido iv
     JOIN public.pedidos v ON ((v.id = iv.cd_pedido)))
  GROUP BY iv.cd_pedido, v.cd_status, v.vl_pedido, v.vl_recebido, v.vl_desconto
  ORDER BY iv.cd_pedido;


ALTER TABLE public.vw_devolvidos_pendentes OWNER TO postgres;

--
-- TOC entry 246 (class 1259 OID 16555)
-- Name: vw_itens_compra; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.vw_itens_compra AS
 SELECT i.id,
    i.cd_compra,
    i.cd_produto,
    p.no_produto,
    p.cd_medida,
    m.no_medida,
    p.vl_compra,
    i.qt_produto,
    i.vl_unitario
   FROM ((public.produtos p
     JOIN public.itens_compra i ON ((p.id = i.cd_produto)))
     JOIN public.medidas m ON ((p.cd_medida = m.id)));


ALTER TABLE public.vw_itens_compra OWNER TO postgres;

--
-- TOC entry 247 (class 1259 OID 16559)
-- Name: vw_itens_venda; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.vw_itens_venda AS
 SELECT i.id,
    i.cd_produto,
    p.no_produto,
    p.cd_medida,
    m.no_medida,
    p.vl_unitario AS vl_venda,
    i.consignado,
    i.vendido,
    i.devolvido,
    i.cd_pedido AS cd_venda,
    i.vl_unitario,
    i.vl_custo,
    (i.vl_unitario * (i.consignado)::numeric) AS vl_consignado,
    (i.vl_unitario * (i.vendido)::numeric) AS vl_vendido,
    ((i.vl_unitario * (i.consignado)::numeric) + (i.vl_unitario * (i.vendido)::numeric)) AS vl_total
   FROM ((public.medidas m
     JOIN public.produtos p ON ((m.id = p.cd_medida)))
     JOIN public.itens_pedido i ON ((p.id = i.cd_produto)))
  ORDER BY i.id;


ALTER TABLE public.vw_itens_venda OWNER TO postgres;

--
-- TOC entry 248 (class 1259 OID 16564)
-- Name: vw_pedidos; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.vw_pedidos AS
 SELECT p.id,
    p.cd_equipe,
    e.no_equipe,
    p.cd_cliente,
    c.no_cliente,
    c.rg_cliente,
    c.cpf_cliente,
    c.dt_nascimento,
    c.lged_cliente,
    c.nred_cliente,
    c.bred_cliente,
    cid.uf,
    cid.no_cidade,
    c.cep_cliente,
    c.tl_cliente,
    c.cl_cliente,
    p.dt_entrega,
    p.dt_vencimento,
    p.cd_status,
    s.st_venda,
    p.cd_vendedor,
    p.vl_desconto,
    p.vl_pedido,
    p.vl_recebido,
    p.vl_brinde,
    p.vlp01,
    p.vlp02,
    p.vlp03,
    p.vlp04,
    p.vlp05,
    p.vlp06,
    p.vlp07,
    p.vlp08,
    p.vlp09,
    p.vlp10,
    p.vlp11,
    p.vlp12,
    p.vlp13,
    p.vlp14,
    p.vlp15,
    p.dtp01,
    p.dtp02,
    p.dtp03,
    p.dtp04,
    p.dtp05,
    p.dtp06,
    p.dtp07,
    p.dtp08,
    p.dtp09,
    p.dtp10,
    p.dtp11,
    p.dtp12,
    p.dtp13,
    p.dtp14,
    p.dtp15,
    f.no_funcionario,
    f.rg_funcionario,
    f.cpf_funcionario,
    f.ed_funcionario,
    f.uf_funcionario,
    f.cid_funcionario,
    f.dt_admissao,
    f.dt_demissao,
    f.cep_funcionario,
    f.tl_funcionario,
    f.nr_cc,
    f.nr_agencia,
    f.no_banco
   FROM (((((public.clientes c
     JOIN public.cidades cid ON ((c.cd_cidade = cid.id)))
     JOIN public.pedidos p ON ((c.id = p.cd_cliente)))
     JOIN public.equipes e ON ((p.cd_equipe = e.id)))
     JOIN public.funcionarios f ON ((p.cd_vendedor = f.id)))
     JOIN public.status_vendas s ON ((p.cd_status = s.id)));


ALTER TABLE public.vw_pedidos OWNER TO postgres;

--
-- TOC entry 249 (class 1259 OID 16569)
-- Name: vw_pedidos_por_vendedor; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.vw_pedidos_por_vendedor AS
SELECT
    NULL::integer AS cod,
    NULL::integer AS cd_vendedor,
    NULL::integer AS cd_funcionario,
    NULL::character varying(50) AS vendedor,
    NULL::numeric(10,2) AS vl_pedido,
    NULL::numeric(10,2) AS vl_comissao,
    NULL::numeric(10,2) AS vl_recebido,
    NULL::numeric AS vl_pendente,
    NULL::bigint AS consignado,
    NULL::bigint AS vendido,
    NULL::bigint AS devolvido,
    NULL::integer AS cd_status,
    NULL::character varying(20) AS st_venda,
    NULL::date AS dt_vencimento;


ALTER TABLE public.vw_pedidos_por_vendedor OWNER TO postgres;

--
-- TOC entry 250 (class 1259 OID 16575)
-- Name: vw_privilegios_by_perfil; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.vw_privilegios_by_perfil AS
 SELECT pri.id,
    pri.cd_rotina,
    rot.no_rotina,
    pri.cd_perfil,
    per.no_perfil,
    per.ds_perfil,
    pri.acessar,
    pri.adicionar,
    pri.editar,
    pri.excluir
   FROM ((public.perfis per
     JOIN public.privilegios pri ON ((per.id = pri.cd_perfil)))
     JOIN public.rotinas rot ON ((pri.cd_rotina = rot.id)))
  ORDER BY pri.cd_perfil;


ALTER TABLE public.vw_privilegios_by_perfil OWNER TO postgres;

--
-- TOC entry 251 (class 1259 OID 16579)
-- Name: vw_produtos; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.vw_produtos AS
 SELECT p.id,
    p.no_produto,
    p.cd_medida,
    m.no_medida,
    p.vl_compra,
    p.vl_unitario AS vl_venda,
    p.estoque,
    p.tp_produto
   FROM (public.medidas m
     JOIN public.produtos p ON ((m.id = p.cd_medida)))
  ORDER BY p.no_produto;


ALTER TABLE public.vw_produtos OWNER TO postgres;

--
-- TOC entry 252 (class 1259 OID 16583)
-- Name: vw_status_vendas; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.vw_status_vendas AS
SELECT
    NULL::integer AS id_vendedor,
    NULL::integer AS cd_vendedor,
    NULL::character varying(50) AS vendedor,
    NULL::integer AS cd_vendas,
    NULL::character varying(20) AS st_venda,
    NULL::date AS dt_vencimento,
    NULL::bigint AS consignado,
    NULL::numeric AS vl_consignado,
    NULL::bigint AS pendente,
    NULL::numeric AS vl_pendente,
    NULL::bigint AS concluido,
    NULL::numeric AS vl_concluido,
    NULL::bigint AS devolvido,
    NULL::numeric AS vl_devolvido,
    NULL::bigint AS perdido,
    NULL::numeric AS vl_perdido,
    NULL::bigint AS total,
    NULL::numeric AS vl_pedido,
    NULL::numeric AS recebido,
    NULL::numeric AS desconto;


ALTER TABLE public.vw_status_vendas OWNER TO postgres;

--
-- TOC entry 3013 (class 2604 OID 16589)
-- Name: brindes_pedido id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.brindes_pedido ALTER COLUMN id SET DEFAULT nextval('public.brindes_venda_id_seq'::regclass);


--
-- TOC entry 3014 (class 2604 OID 16590)
-- Name: cidades id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cidades ALTER COLUMN id SET DEFAULT nextval('public.cidades_id_seq'::regclass);


--
-- TOC entry 3015 (class 2604 OID 16591)
-- Name: clientes id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.clientes ALTER COLUMN id SET DEFAULT nextval('public.clientes_id_seq'::regclass);


--
-- TOC entry 3016 (class 2604 OID 16592)
-- Name: compras id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.compras ALTER COLUMN id SET DEFAULT nextval('public.compras_id_seq'::regclass);


--
-- TOC entry 3018 (class 2604 OID 16593)
-- Name: equipes id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.equipes ALTER COLUMN id SET DEFAULT nextval('public.equipes_id_seq'::regclass);


--
-- TOC entry 3019 (class 2604 OID 16594)
-- Name: fornecedores id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fornecedores ALTER COLUMN id SET DEFAULT nextval('public.fornecedores_id_seq'::regclass);


--
-- TOC entry 3020 (class 2604 OID 16595)
-- Name: funcionarios id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.funcionarios ALTER COLUMN id SET DEFAULT nextval('public.funcionarios_id_seq'::regclass);


--
-- TOC entry 3023 (class 2604 OID 16596)
-- Name: itens_compra id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.itens_compra ALTER COLUMN id SET DEFAULT nextval('public.itens_compra_id_seq'::regclass);


--
-- TOC entry 3028 (class 2604 OID 16597)
-- Name: itens_pedido id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.itens_pedido ALTER COLUMN id SET DEFAULT nextval('public.itens_venda_id_seq'::regclass);


--
-- TOC entry 3029 (class 2604 OID 16598)
-- Name: medidas id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.medidas ALTER COLUMN id SET DEFAULT nextval('public.medidas_id_seq'::regclass);


--
-- TOC entry 3031 (class 2604 OID 16599)
-- Name: motivos_perdas id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.motivos_perdas ALTER COLUMN id SET DEFAULT nextval('public.motivos_perdas_id_seq'::regclass);


--
-- TOC entry 3037 (class 2604 OID 16600)
-- Name: pedidos id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pedidos ALTER COLUMN id SET DEFAULT nextval('public.pedidos_id_seq'::regclass);


--
-- TOC entry 3038 (class 2604 OID 16601)
-- Name: perfis id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.perfis ALTER COLUMN id SET DEFAULT nextval('public.perfis_id_seq'::regclass);


--
-- TOC entry 3043 (class 2604 OID 16602)
-- Name: privilegios id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.privilegios ALTER COLUMN id SET DEFAULT nextval('public.privilegios_id_seq'::regclass);


--
-- TOC entry 3047 (class 2604 OID 16603)
-- Name: produtos id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.produtos ALTER COLUMN id SET DEFAULT nextval('public.produtos_id_seq'::regclass);


--
-- TOC entry 3030 (class 2604 OID 16604)
-- Name: rotinas id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rotinas ALTER COLUMN id SET DEFAULT nextval('public.modulos_id_seq'::regclass);


--
-- TOC entry 3048 (class 2604 OID 16605)
-- Name: status_vendas id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.status_vendas ALTER COLUMN id SET DEFAULT nextval('public.status_vendas_id_seq'::regclass);


--
-- TOC entry 3050 (class 2604 OID 16606)
-- Name: usuarios id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios ALTER COLUMN id SET DEFAULT nextval('public.usuarios_id_seq'::regclass);


--
-- TOC entry 3060 (class 2606 OID 16608)
-- Name: configuracoes id_pk_configuracao; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.configuracoes
    ADD CONSTRAINT id_pk_configuracao PRIMARY KEY (id);


--
-- TOC entry 3065 (class 2606 OID 16610)
-- Name: fornecedores id_pk_fornecedor; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fornecedores
    ADD CONSTRAINT id_pk_fornecedor PRIMARY KEY (id);


--
-- TOC entry 3052 (class 2606 OID 16612)
-- Name: brindes_pedido pk_id_brinde_venda; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.brindes_pedido
    ADD CONSTRAINT pk_id_brinde_venda PRIMARY KEY (id);


--
-- TOC entry 3054 (class 2606 OID 16614)
-- Name: cidades pk_id_cidade; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cidades
    ADD CONSTRAINT pk_id_cidade PRIMARY KEY (id);


--
-- TOC entry 3056 (class 2606 OID 16616)
-- Name: clientes pk_id_cliente; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.clientes
    ADD CONSTRAINT pk_id_cliente PRIMARY KEY (id);


--
-- TOC entry 3058 (class 2606 OID 16618)
-- Name: compras pk_id_compra; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.compras
    ADD CONSTRAINT pk_id_compra PRIMARY KEY (id);


--
-- TOC entry 3063 (class 2606 OID 16620)
-- Name: equipes pk_id_equipe; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.equipes
    ADD CONSTRAINT pk_id_equipe PRIMARY KEY (id);


--
-- TOC entry 3068 (class 2606 OID 16622)
-- Name: funcionarios pk_id_funcionario; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.funcionarios
    ADD CONSTRAINT pk_id_funcionario PRIMARY KEY (id);


--
-- TOC entry 3070 (class 2606 OID 16624)
-- Name: itens_compra pk_id_item_compra; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.itens_compra
    ADD CONSTRAINT pk_id_item_compra PRIMARY KEY (id);


--
-- TOC entry 3072 (class 2606 OID 16626)
-- Name: itens_pedido pk_id_item_venda; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.itens_pedido
    ADD CONSTRAINT pk_id_item_venda PRIMARY KEY (id);


--
-- TOC entry 3074 (class 2606 OID 16628)
-- Name: medidas pk_id_medida; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.medidas
    ADD CONSTRAINT pk_id_medida PRIMARY KEY (id);


--
-- TOC entry 3076 (class 2606 OID 16630)
-- Name: rotinas pk_id_modulo; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rotinas
    ADD CONSTRAINT pk_id_modulo PRIMARY KEY (id);


--
-- TOC entry 3078 (class 2606 OID 16632)
-- Name: motivos_perdas pk_id_motivos; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.motivos_perdas
    ADD CONSTRAINT pk_id_motivos PRIMARY KEY (id);


--
-- TOC entry 3083 (class 2606 OID 16634)
-- Name: perfis pk_id_perfil; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.perfis
    ADD CONSTRAINT pk_id_perfil PRIMARY KEY (id);


--
-- TOC entry 3085 (class 2606 OID 16636)
-- Name: privilegios pk_id_privilegio; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.privilegios
    ADD CONSTRAINT pk_id_privilegio PRIMARY KEY (id);


--
-- TOC entry 3088 (class 2606 OID 16638)
-- Name: produtos pk_id_produto; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.produtos
    ADD CONSTRAINT pk_id_produto PRIMARY KEY (id);


--
-- TOC entry 3090 (class 2606 OID 16640)
-- Name: status_vendas pk_id_status_venda; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.status_vendas
    ADD CONSTRAINT pk_id_status_venda PRIMARY KEY (id);


--
-- TOC entry 3093 (class 2606 OID 16642)
-- Name: usuarios pk_id_usuario; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT pk_id_usuario PRIMARY KEY (id);


--
-- TOC entry 3080 (class 2606 OID 16644)
-- Name: pedidos pk_id_venda; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pedidos
    ADD CONSTRAINT pk_id_venda PRIMARY KEY (id);


--
-- TOC entry 3091 (class 1259 OID 16645)
-- Name: index_login; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX index_login ON public.usuarios USING btree (login);


--
-- TOC entry 3061 (class 1259 OID 16646)
-- Name: index_nome_equipe; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX index_nome_equipe ON public.equipes USING btree (no_equipe);


--
-- TOC entry 3066 (class 1259 OID 16647)
-- Name: index_nome_fornecedor; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX index_nome_fornecedor ON public.fornecedores USING btree (no_fornecedor);


--
-- TOC entry 3081 (class 1259 OID 16648)
-- Name: index_nome_perfil; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX index_nome_perfil ON public.perfis USING btree (no_perfil);


--
-- TOC entry 3086 (class 1259 OID 16649)
-- Name: index_nome_produto; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX index_nome_produto ON public.produtos USING btree (no_produto);


--
-- TOC entry 3248 (class 2618 OID 16650)
-- Name: vw_pedidos_por_vendedor _RETURN; Type: RULE; Schema: public; Owner: postgres
--

CREATE OR REPLACE VIEW public.vw_pedidos_por_vendedor AS
 SELECT v.id AS cod,
    v.cd_vendedor,
    f.id AS cd_funcionario,
    f.no_funcionario AS vendedor,
    v.vl_pedido,
    v.vl_desconto AS vl_comissao,
    v.vl_recebido,
    (v.vl_pedido - v.vl_recebido) AS vl_pendente,
    sum(i.consignado) AS consignado,
    sum(i.vendido) AS vendido,
    sum(i.devolvido) AS devolvido,
    v.cd_status,
    s.st_venda,
    v.dt_vencimento
   FROM (((public.funcionarios f
     JOIN public.pedidos v ON ((f.id = v.cd_vendedor)))
     JOIN public.status_vendas s ON ((v.cd_status = s.id)))
     JOIN public.itens_pedido i ON ((v.id = i.cd_pedido)))
  GROUP BY v.id, v.cd_vendedor, f.id, f.no_funcionario, s.st_venda;


--
-- TOC entry 3249 (class 2618 OID 16652)
-- Name: vw_status_vendas _RETURN; Type: RULE; Schema: public; Owner: postgres
--

CREATE OR REPLACE VIEW public.vw_status_vendas AS
 SELECT f.id AS id_vendedor,
    v.cd_vendedor,
    f.no_funcionario AS vendedor,
    v.id AS cd_vendas,
    s.st_venda,
    v.dt_vencimento,
    ( SELECT count(s.st_venda) AS count
          WHERE ((s.st_venda)::text = 'CONSIGNADO'::text)) AS consignado,
    ( SELECT sum(v.vl_pedido) AS sum
          WHERE ((s.st_venda)::text = 'CONSIGNADO'::text)) AS vl_consignado,
    ( SELECT count(s.st_venda) AS count
          WHERE ((s.st_venda)::text = 'PENDENTE'::text)) AS pendente,
    ( SELECT sum((v.vl_pedido - v.vl_recebido)) AS sum
          WHERE ((s.st_venda)::text = 'PENDENTE'::text)) AS vl_pendente,
    ( SELECT count(s.st_venda) AS count
          WHERE ((s.st_venda)::text = 'CONCLUÍDO'::text)) AS concluido,
    ( SELECT sum(v.vl_recebido) AS sum
          WHERE ((s.st_venda)::text = 'CONCLUÍDO'::text)) AS vl_concluido,
    ( SELECT count(s.st_venda) AS count
          WHERE ((s.st_venda)::text = 'DEVOLVIDO'::text)) AS devolvido,
    ( SELECT sum(v.vl_pedido) AS sum
          WHERE ((s.st_venda)::text = 'DEVOLVIDO'::text)) AS vl_devolvido,
    ( SELECT count(s.st_venda) AS count
          WHERE ((s.st_venda)::text = 'PERDIDO'::text)) AS perdido,
    ( SELECT sum(v.vl_pedido) AS sum
          WHERE ((s.st_venda)::text = 'PERDIDO'::text)) AS vl_perdido,
    count(v.id) AS total,
    sum(v.vl_pedido) AS vl_pedido,
    sum(v.vl_recebido) AS recebido,
    sum(v.vl_desconto) AS desconto
   FROM ((public.status_vendas s
     JOIN public.pedidos v ON ((s.id = v.cd_status)))
     JOIN public.funcionarios f ON ((v.cd_vendedor = f.id)))
  GROUP BY f.no_funcionario, f.id, v.cd_vendedor, v.id, s.st_venda;


--
-- TOC entry 3096 (class 2606 OID 16654)
-- Name: clientes fk_cd_cidade; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.clientes
    ADD CONSTRAINT fk_cd_cidade FOREIGN KEY (cd_cidade) REFERENCES public.cidades(id);


--
-- TOC entry 3097 (class 2606 OID 16659)
-- Name: compras fk_cd_fornecedor; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.compras
    ADD CONSTRAINT fk_cd_fornecedor FOREIGN KEY (cd_fornecedor) REFERENCES public.fornecedores(id);


--
-- TOC entry 3109 (class 2606 OID 16664)
-- Name: produtos fk_cd_medida; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.produtos
    ADD CONSTRAINT fk_cd_medida FOREIGN KEY (cd_medida) REFERENCES public.medidas(id);


--
-- TOC entry 3107 (class 2606 OID 16669)
-- Name: privilegios fk_cd_modulo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.privilegios
    ADD CONSTRAINT fk_cd_modulo FOREIGN KEY (cd_rotina) REFERENCES public.rotinas(id);


--
-- TOC entry 3110 (class 2606 OID 16674)
-- Name: usuarios fk_cd_perfil; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT fk_cd_perfil FOREIGN KEY (cd_perfil) REFERENCES public.perfis(id);


--
-- TOC entry 3108 (class 2606 OID 16679)
-- Name: privilegios fk_cd_perfil; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.privilegios
    ADD CONSTRAINT fk_cd_perfil FOREIGN KEY (cd_perfil) REFERENCES public.perfis(id) ON DELETE CASCADE;


--
-- TOC entry 3102 (class 2606 OID 16684)
-- Name: pedidos fk_clientes; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pedidos
    ADD CONSTRAINT fk_clientes FOREIGN KEY (cd_cliente) REFERENCES public.clientes(id);


--
-- TOC entry 3103 (class 2606 OID 16689)
-- Name: pedidos fk_equipes; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pedidos
    ADD CONSTRAINT fk_equipes FOREIGN KEY (cd_equipe) REFERENCES public.equipes(id);


--
-- TOC entry 3104 (class 2606 OID 16694)
-- Name: pedidos fk_funcionario; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pedidos
    ADD CONSTRAINT fk_funcionario FOREIGN KEY (cd_vendedor) REFERENCES public.funcionarios(id);


--
-- TOC entry 3098 (class 2606 OID 16699)
-- Name: itens_compra fk_id_compra; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.itens_compra
    ADD CONSTRAINT fk_id_compra FOREIGN KEY (cd_compra) REFERENCES public.compras(id);


--
-- TOC entry 3100 (class 2606 OID 16704)
-- Name: itens_pedido fk_id_produto; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.itens_pedido
    ADD CONSTRAINT fk_id_produto FOREIGN KEY (cd_produto) REFERENCES public.produtos(id);


--
-- TOC entry 3099 (class 2606 OID 16709)
-- Name: itens_compra fk_id_produto; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.itens_compra
    ADD CONSTRAINT fk_id_produto FOREIGN KEY (cd_produto) REFERENCES public.produtos(id);


--
-- TOC entry 3094 (class 2606 OID 16714)
-- Name: brindes_pedido fk_id_produtos; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.brindes_pedido
    ADD CONSTRAINT fk_id_produtos FOREIGN KEY (cd_produto) REFERENCES public.produtos(id);


--
-- TOC entry 3101 (class 2606 OID 16719)
-- Name: itens_pedido fk_id_venda; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.itens_pedido
    ADD CONSTRAINT fk_id_venda FOREIGN KEY (cd_pedido) REFERENCES public.pedidos(id) ON DELETE CASCADE;


--
-- TOC entry 3095 (class 2606 OID 16724)
-- Name: brindes_pedido fk_id_venda; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.brindes_pedido
    ADD CONSTRAINT fk_id_venda FOREIGN KEY (cd_pedido) REFERENCES public.pedidos(id) ON DELETE CASCADE;


--
-- TOC entry 3105 (class 2606 OID 16729)
-- Name: pedidos fk_mtperda; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pedidos
    ADD CONSTRAINT fk_mtperda FOREIGN KEY (cd_mtperda) REFERENCES public.motivos_perdas(id);


--
-- TOC entry 3106 (class 2606 OID 16734)
-- Name: pedidos fk_status; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pedidos
    ADD CONSTRAINT fk_status FOREIGN KEY (cd_status) REFERENCES public.status_vendas(id);


-- Completed on 2022-10-17 21:27:47 -03

--
-- PostgreSQL database dump complete
--

