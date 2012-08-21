-- CREATE SCHEMA `check` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci;

alter table FACTURA drop foreign key FK_EMPRESA_FACTURA;
alter table FACTURA drop foreign key FK_CHEQUE_FACTURA;
alter table CHEQUE drop foreign key FK_EMPRESA_CHEQUE;

drop table CHEQUE;
drop table EMPRESA;
drop table FACTURA;
drop table PARAMETRO;

create table CHEQUE
(
   NUMERO_CHEQUE        varchar(20) not null,
   FECHA_CHEQUE         date not null,
   FRACCION_CHEQUE      varchar(5) not null,
   primary key (NUMERO_CHEQUE)
);

create table EMPRESA
(
   RUT_EMPRESA          varchar(12) not null,
   RAZON_SOCIAL_EMPRESA varchar(50) not null,
   RUT_REPRESENTANTE    varchar(12),
   RAZON_SOCIAL_REPRESENTANTE varchar(50) not null,
   ACTIVO_EMPRESA       boolean not null,
   primary key (RUT_EMPRESA)
);

create table FACTURA
(
   NUMERO_FACTURA       bigint not null,
   NUMERO_CHEQUE        varchar(20),
   RUT_EMPRESA          varchar(12) not null,
   FECHA_FACTURA        date not null,
   MONTO_FACTURA        bigint not null,
   ESTADO_FACTURA       varchar(10) not null,
   ACTIVO_FACTURA       boolean not null,
   primary key (NUMERO_FACTURA)
);

create table FACTURA_CHEQUE
(
  NUMERO_FACTURA		bigint not null,
  NUMERO_CHEQUE			varchar(20) not null,
  primary key (NUMERO_FACTURA, NUMERO_CHEQUE)
);

create table PARAMETRO
(
   CLAVE_PARAMETRO      varchar(32) not null,
   VALOR_PARAMETRO      varchar(128) not null,
   DESCRIPCION_PARAMETRO bigint not null,
   ACTIVO_PARAMETRO     boolean not null,
   primary key (CLAVE_PARAMETRO)
);

alter table CHEQUE add constraint FK_EMPRESA_CHEQUE foreign key (RUT_EMPRESA)
      references EMPRESA (RUT_EMPRESA) on delete restrict on update restrict;
alter table FACTURA add constraint FK_CHEQUE_FACTURA foreign key (NUMERO_CHEQUE)
      references CHEQUE (NUMERO_CHEQUE) on delete restrict on update restrict;
alter table FACTURA add constraint FK_EMPRESA_FACTURA foreign key (RUT_EMPRESA)
      references EMPRESA (RUT_EMPRESA) on delete restrict on update restrict;