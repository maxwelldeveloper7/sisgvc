SELECT
     v.id AS id,
     v.nr_pedido AS nr_pedido,
     v.cd_equipe AS cd_equipe,
     e.no_equipe AS no_equipe,
     v.cd_cliente AS cd_cliente,
     c.no_cliente AS no_cliente,
     c.rg_cliente AS rg_cliente,
     c.cpf_cliente AS cpf_cliente,
     c.dt_nascimento AS dt_nascimento,
     c.ed_cliente AS ed_cliente,
     c.uf_cliente AS uf_cliente,
     c.cid_cliente AS cid_cliente,
     c.cep_cliente AS cep_cliente,
     c.tl_cliente AS tl_cliente,
     c.cl_cliente AS cl_cliente,
     v.dt_entrega AS dt_entrega,
     v.dt_vencimento AS dt_vencimento,
     v.cd_status AS cd_status,
     s.st_venda AS st_venda,
     v.cd_vendedor AS cd_vendedor,
     f.no_funcionario AS no_funcionario,
     f.rg_funcionario AS rg_funcionario,
     f.cpf_funcionario AS cpf_funcionario,
     f.ed_funcionario AS ed_funcionario,
     f.uf_funcionario AS uf_funcionario,
     f.cid_funcionario AS cid_funcionario,
     f.dt_admissao AS dt_admissao,
     f.dt_demissao AS dt_demissao,
     f.cep_funcionario AS cep_funcionario,
     f.tl_funcionario AS tl_funcionario,
     f.nr_cc AS nr_cc,
     f.nr_agencia AS nr_agencia,
     f.no_banco AS no_banco
FROM
     public.clientes c INNER JOIN public.vendas v ON c.id = v.cd_cliente
     INNER JOIN public.equipes e ON v.cd_equipe = e.id
     INNER JOIN public.funcionarios f ON v.cd_vendedor = f.id
     INNER JOIN public.status_vendas s ON v.cd_status = s.id
