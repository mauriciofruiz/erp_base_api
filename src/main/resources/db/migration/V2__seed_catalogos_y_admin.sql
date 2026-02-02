-- V2: Seed catálogos (tipocontribuyente, país, provincia, parroquia, estadocivil, género,
--     identificación fiscal), empresa, sucursal, persona, usuario administrador, rol y rolusuario

-- 1. Tipo contribuyente
INSERT INTO erp."tipocontribuyente" ("id_tipocontribuyente", "nombre_tipocontribuyente", "cod_tipocontribuyente")
VALUES ('NATURAL', 'Persona Natural', '01');

-- 2. Países del mundo (Ecuador primero para referencias, luego resto por orden alfabético)
INSERT INTO erp."pais" ("id_pais", "codigopais", "nombre_pais") VALUES
(1, 'EC', 'Ecuador'),
(2, 'AF', 'Afghanistan'),
(3, 'AL', 'Albania'),
(4, 'DZ', 'Algeria'),
(5, 'AD', 'Andorra'),
(6, 'AO', 'Angola'),
(7, 'AG', 'Antigua and Barbuda'),
(8, 'AR', 'Argentina'),
(9, 'AM', 'Armenia'),
(10, 'AU', 'Australia'),
(11, 'AT', 'Austria'),
(12, 'AZ', 'Azerbaijan'),
(13, 'BS', 'Bahamas'),
(14, 'BH', 'Bahrain'),
(15, 'BD', 'Bangladesh'),
(16, 'BB', 'Barbados'),
(17, 'BY', 'Belarus'),
(18, 'BE', 'Belgium'),
(19, 'BZ', 'Belize'),
(20, 'BJ', 'Benin'),
(21, 'BT', 'Bhutan'),
(22, 'BO', 'Bolivia'),
(23, 'BA', 'Bosnia and Herzegovina'),
(24, 'BW', 'Botswana'),
(25, 'BR', 'Brazil'),
(26, 'BN', 'Brunei'),
(27, 'BG', 'Bulgaria'),
(28, 'BF', 'Burkina Faso'),
(29, 'BI', 'Burundi'),
(30, 'KH', 'Cambodia'),
(31, 'CM', 'Cameroon'),
(32, 'CA', 'Canada'),
(33, 'CV', 'Cape Verde'),
(34, 'CF', 'Central African Republic'),
(35, 'TD', 'Chad'),
(36, 'CL', 'Chile'),
(37, 'CN', 'China'),
(38, 'CO', 'Colombia'),
(39, 'KM', 'Comoros'),
(40, 'CG', 'Congo'),
(41, 'CD', 'Democratic Republic of the Congo'),
(42, 'CR', 'Costa Rica'),
(43, 'HR', 'Croatia'),
(44, 'CU', 'Cuba'),
(45, 'CY', 'Cyprus'),
(46, 'CZ', 'Czech Republic'),
(47, 'DK', 'Denmark'),
(48, 'DJ', 'Djibouti'),
(49, 'DM', 'Dominica'),
(50, 'DO', 'Dominican Republic'),
(51, 'EG', 'Egypt'),
(52, 'SV', 'El Salvador'),
(53, 'GQ', 'Equatorial Guinea'),
(54, 'ER', 'Eritrea'),
(55, 'EE', 'Estonia'),
(56, 'ET', 'Ethiopia'),
(57, 'FJ', 'Fiji'),
(58, 'FI', 'Finland'),
(59, 'FR', 'France'),
(60, 'GA', 'Gabon'),
(61, 'GM', 'Gambia'),
(62, 'GE', 'Georgia'),
(63, 'DE', 'Germany'),
(64, 'GH', 'Ghana'),
(65, 'GR', 'Greece'),
(66, 'GD', 'Grenada'),
(67, 'GT', 'Guatemala'),
(68, 'GN', 'Guinea'),
(69, 'GW', 'Guinea-Bissau'),
(70, 'GY', 'Guyana'),
(71, 'HT', 'Haiti'),
(72, 'HN', 'Honduras'),
(73, 'HU', 'Hungary'),
(74, 'IS', 'Iceland'),
(75, 'IN', 'India'),
(76, 'ID', 'Indonesia'),
(77, 'IR', 'Iran'),
(78, 'IQ', 'Iraq'),
(79, 'IE', 'Ireland'),
(80, 'IL', 'Israel'),
(81, 'IT', 'Italy'),
(82, 'JM', 'Jamaica'),
(83, 'JP', 'Japan'),
(84, 'JO', 'Jordan'),
(85, 'KZ', 'Kazakhstan'),
(86, 'KE', 'Kenya'),
(87, 'KI', 'Kiribati'),
(88, 'KP', 'North Korea'),
(89, 'KR', 'South Korea'),
(90, 'KW', 'Kuwait'),
(91, 'KG', 'Kyrgyzstan'),
(92, 'LA', 'Laos'),
(93, 'LV', 'Latvia'),
(94, 'LB', 'Lebanon'),
(95, 'LS', 'Lesotho'),
(96, 'LR', 'Liberia'),
(97, 'LY', 'Libya'),
(98, 'LI', 'Liechtenstein'),
(99, 'LT', 'Lithuania'),
(100, 'LU', 'Luxembourg'),
(101, 'MK', 'North Macedonia'),
(102, 'MG', 'Madagascar'),
(103, 'MW', 'Malawi'),
(104, 'MY', 'Malaysia'),
(105, 'MV', 'Maldives'),
(106, 'ML', 'Mali'),
(107, 'MT', 'Malta'),
(108, 'MH', 'Marshall Islands'),
(109, 'MR', 'Mauritania'),
(110, 'MU', 'Mauritius'),
(111, 'MX', 'Mexico'),
(112, 'FM', 'Micronesia'),
(113, 'MD', 'Moldova'),
(114, 'MC', 'Monaco'),
(115, 'MN', 'Mongolia'),
(116, 'ME', 'Montenegro'),
(117, 'MA', 'Morocco'),
(118, 'MZ', 'Mozambique'),
(119, 'MM', 'Myanmar'),
(120, 'NA', 'Namibia'),
(121, 'NR', 'Nauru'),
(122, 'NP', 'Nepal'),
(123, 'NL', 'Netherlands'),
(124, 'NZ', 'New Zealand'),
(125, 'NI', 'Nicaragua'),
(126, 'NE', 'Niger'),
(127, 'NG', 'Nigeria'),
(128, 'NO', 'Norway'),
(129, 'OM', 'Oman'),
(130, 'PK', 'Pakistan'),
(131, 'PW', 'Palau'),
(132, 'PS', 'Palestine'),
(133, 'PA', 'Panama'),
(134, 'PG', 'Papua New Guinea'),
(135, 'PY', 'Paraguay'),
(136, 'PE', 'Peru'),
(137, 'PH', 'Philippines'),
(138, 'PL', 'Poland'),
(139, 'PT', 'Portugal'),
(140, 'QA', 'Qatar'),
(141, 'RO', 'Romania'),
(142, 'RU', 'Russia'),
(143, 'RW', 'Rwanda'),
(144, 'KN', 'Saint Kitts and Nevis'),
(145, 'LC', 'Saint Lucia'),
(146, 'VC', 'Saint Vincent and the Grenadines'),
(147, 'WS', 'Samoa'),
(148, 'SM', 'San Marino'),
(149, 'ST', 'Sao Tome and Principe'),
(150, 'SA', 'Saudi Arabia'),
(151, 'SN', 'Senegal'),
(152, 'RS', 'Serbia'),
(153, 'SC', 'Seychelles'),
(154, 'SL', 'Sierra Leone'),
(155, 'SG', 'Singapore'),
(156, 'SK', 'Slovakia'),
(157, 'SI', 'Slovenia'),
(158, 'SB', 'Solomon Islands'),
(159, 'SO', 'Somalia'),
(160, 'ZA', 'South Africa'),
(161, 'SS', 'South Sudan'),
(162, 'ES', 'Spain'),
(163, 'LK', 'Sri Lanka'),
(164, 'SD', 'Sudan'),
(165, 'SR', 'Suriname'),
(166, 'SZ', 'Eswatini'),
(167, 'SE', 'Sweden'),
(168, 'CH', 'Switzerland'),
(169, 'SY', 'Syria'),
(170, 'TW', 'Taiwan'),
(171, 'TJ', 'Tajikistan'),
(172, 'TZ', 'Tanzania'),
(173, 'TH', 'Thailand'),
(174, 'TL', 'Timor-Leste'),
(175, 'TG', 'Togo'),
(176, 'TO', 'Tonga'),
(177, 'TT', 'Trinidad and Tobago'),
(178, 'TN', 'Tunisia'),
(179, 'TR', 'Turkey'),
(180, 'TM', 'Turkmenistan'),
(181, 'TV', 'Tuvalu'),
(182, 'UG', 'Uganda'),
(183, 'UA', 'Ukraine'),
(184, 'AE', 'United Arab Emirates'),
(185, 'GB', 'United Kingdom'),
(186, 'US', 'United States'),
(187, 'UY', 'Uruguay'),
(188, 'UZ', 'Uzbekistan'),
(189, 'VU', 'Vanuatu'),
(190, 'VA', 'Vatican City'),
(191, 'VE', 'Venezuela'),
(192, 'VN', 'Vietnam'),
(193, 'YE', 'Yemen'),
(194, 'ZM', 'Zambia'),
(195, 'ZW', 'Zimbabwe');

-- 3. Provincias de Ecuador (24 provincias, código INEC)
INSERT INTO erp."provincia" ("id_provincia", "id_pais", "nombre_provincia", "cod_region") VALUES
('01', 1, 'Azuay', 7),
('02', 1, 'Bolívar', 5),
('03', 1, 'Cañar', 7),
('04', 1, 'Carchi', 2),
('05', 1, 'Chimborazo', 6),
('06', 1, 'Cotopaxi', 5),
('07', 1, 'El Oro', 8),
('08', 1, 'Esmeraldas', 2),
('09', 1, 'Galápagos', 8),
('10', 1, 'Guayas', 8),
('11', 1, 'Imbabura', 2),
('12', 1, 'Loja', 7),
('13', 1, 'Los Ríos', 8),
('14', 1, 'Manabí', 8),
('15', 1, 'Morona Santiago', 7),
('16', 1, 'Napo', 3),
('17', 1, 'Pichincha', 4),
('18', 1, 'Tungurahua', 6),
('19', 1, 'Pastaza', 3),
('20', 1, 'Zamora Chinchipe', 7),
('21', 1, 'Sucumbíos', 3),
('22', 1, 'Orellana', 3),
('23', 1, 'Santo Domingo de los Tsáchilas', 5),
('24', 1, 'Santa Elena', 8);

-- Ciudad Quito (Pichincha) para parroquias
INSERT INTO erp."ciudad" ("id_ciudad", "id_provincia", "nombre_ciudad", "codigo_ciudad")
VALUES (1, '17', 'Quito', '01');

-- 4. Parroquias de Quito (10 parroquias urbanas)
INSERT INTO erp."parroquia" ("id_parroquia", "id_ciudad", "nombre_parroquia", "cod_parroquia") VALUES
(1, 1, 'Belisario Quevedo', '001'),
(2, 1, 'Carapungo', '002'),
(3, 1, 'Centro Histórico', '003'),
(4, 1, 'Chilibulo', '004'),
(5, 1, 'Chillogallo', '005'),
(6, 1, 'Cotocollao', '006'),
(7, 1, 'El Condado', '007'),
(8, 1, 'Iñaquito', '008'),
(9, 1, 'Itchimbía', '009'),
(10, 1, 'Jipijapa', '010');

-- 5. Estado civil
INSERT INTO erp."estadocivil" ("id_estadocivil", "nombre_estadocivil", "cod_estadocivil") VALUES
(1, 'Soltero/a', 'S'),
(2, 'Casado/a', 'C'),
(3, 'Divorciado/a', 'D'),
(4, 'Viudo/a', 'V'),
(5, 'Unión libre', 'U');

-- 6. Género
INSERT INTO erp."genero" ("id_genero", "nombre_genero", "cod_genero") VALUES
(1, 'Masculino', 'M'),
(2, 'Femenino', 'F'),
(3, 'Otro', 'O');

-- 7. Identificación fiscal (Ecuador SRI)
INSERT INTO erp."identificacionfiscal" ("id_identificacionfiscal", "cod_identificacionfiscal", "nombre_identificacionfiscal", "codats_identificacionfiscal") VALUES
('04', '04', 'RUC', '04'),
('05', '05', 'Cédula', '05'),
('06', '06', 'Pasaporte', '06'),
('07', '07', 'Venta a consumidor final', '07'),
('08', '08', 'Identificación del exterior', '08');

-- 8. Empresa
INSERT INTO erp."empresa" (
    "emp_id", "emp_codigo", "emp_razonsocial", "emp_ciudad", "emp_direccion",
    "emp_rucempresa", "emp_telefono", "emp_correoelectronico", "emp_representante",
    "emp_rucrepresentante", "emp_numdecimalespvp", "emp_obligadocontabilidad",
    "emp_contribuyenteespecial", "emp_agenteretiene", "emp_host"
) VALUES (
    1, 'EMP001', 'Mi Empresa S.A.', 'Quito', 'Av. Principal 123',
    '1234567890001', '022123456', 'contacto@miempresa.com.ec', 'Juan Pérez',
    '1234567890', 2, TRUE, NULL, FALSE, NULL
);

-- 9. Sucursal (id_ciudad, id_categoriasucursal, id_region opcionales)
INSERT INTO erp."sucursal" (
    "id_sucursal", "id_ciudad", "nombre_sucursal", "nemonico_sucursal",
    "direccion_sucursal", "telefono_sucursal", "correo_sucursal",
    "estado_sucursal", "codigo_sucursal"
) VALUES (
    1, 1, 'Sucursal Principal', 'SP',
    'Av. Principal 123, Quito', '022123456', 'sucursal@miempresa.com.ec',
    TRUE, '001'
);

-- 10. Persona (administrador)
INSERT INTO erp."persona" (
    "id_persona", "id_tipocontribuyente", "id_pais", "id_parroquia",
    "id_estadocivil", "id_genero", "id_identificacionfiscal",
    "identificacion_persona", "nombre_persona", "direccion_persona",
    "correo_persona", "escliente_persona", "esempleado_persona", "esproveedor_persona"
) VALUES (
    1, 'NATURAL', 1, 1,
    1, 1, '05',
    '1234567890', 'Administrador Sistema', 'Av. Principal 123',
    'admin@miempresa.com.ec', FALSE, TRUE, FALSE
);

-- 11. Usuario administrador (password hash dado)
INSERT INTO erp."usuario" (
    "id_usr", "id_sucursal", "id_persona", "nick_usr", "pass_usr", "horainicio_usr", "horafin_usr",
    "estado_usr"
) VALUES (
    1, 1, 1, 'ADMIN', 'ca98d44e0e97d4b8ad9b0ead8fd719ce09b9da89076762f15e756661fe48a616',
    '00:00', '23:59', TRUE
);

-- 12. Rol (administrador)
INSERT INTO erp."rol" (
    "id_rol", "nombre_rol", "descripcion_rol"
) VALUES (
    1, 'Administrador', 'Rol con acceso total al sistema'
);

-- 13. Rol usuario (asignar rol Administrador al usuario admin)
INSERT INTO erp."rolusuario" (
    "id_rolusuario", "id_usr", "id_rol", "estado_rolusuario"
) VALUES (
    1, 1, 1, TRUE
);
