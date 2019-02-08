CREATE TABLE usuario (
  idusuario serial NOT NULL,
  nombre text NOT NULL,
  correo  text NOT NULL,
  contrasenia text NOT NULL,
  fechanacimiento date,
  PRIMARY KEY (idusuario)
);

CREATE TABLE marcador (
  idmarcador serial NOT NULL,
  descripcion text NOT NULL,
  longitud double precision NOT NULL,
  latitud double precision NOT NULL,
  PRIMARY KEY (idmarcador),
  usuarioid integer REFERENCES usuario(idusuario)
);
