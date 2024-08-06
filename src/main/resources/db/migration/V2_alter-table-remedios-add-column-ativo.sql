ALTER TABLE remedio add ativo tinyint;
update remedio set ativo = 1 where id <> 0;