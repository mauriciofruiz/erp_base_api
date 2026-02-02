-- 1. Insertar menus
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(51, 'Compras Exterior', 4, 2, 'ShoppingCartRounded', 'Menu', '/adquisiciones/compras-exterior', 34, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(239, 'Retencion Clientes New', 113, 4, NULL, 'Page', '/ventas/clientes/retencion-clientes/new', 239, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(240, 'Retencion Clientes View', 113, 4, NULL, 'Page', '/ventas/clientes/retencion-clientes/edit-view', 240, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(82, 'Acceso Documentos', 80, 3, 'ShoppingCartRounded', 'Page', '/reprocesos/acceso-documentos', 82, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(241, 'Cierre Producción', 73, 3, 'ShoppingCartRounded', 'Page', '/admin/contabilidad/cierre-produccion', 241, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(242, 'Kardex Despacho', 6, 2, 'WarehouseRounded', 'Page', '/almacenamiento/kardex-despacho', 28, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(243, 'Depositos New', 118, 4, NULL, 'Page', '/bancos/ingresos-depositos/depositos/new', 242, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(245, 'Boletas', 117, 3, 'ShoppingCartRounded', 'Page', '/bancos/ingresos-depositos/boletas', 244, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(244, 'Depositos View', 118, 4, NULL, 'Page', '/bancos/ingresos-depositos/depositos/edit-view', 243, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(39, 'Devolución Compra View', 37, 4, NULL, 'Page', '/adquisiciones/compras-locales/devolucion-compra/edit-view', 39, false);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(38, 'Devolución Compra New', 37, 4, NULL, 'Page', '/adquisiciones/compras-locales/devolucion-compra/new', 38, false);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(44, 'Consultas Reportes', 4, 2, 'SummarizeRoundedIcon', 'Page', '/adquisiciones/compras-locales/consultas-reportes', 51, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(1, 'Home', NULL, 0, 'HomeRounded', 'Page', '/', 1, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(2, 'Administración', 1, 1, 'AdminPanelSettingsRounded', 'Menu', '/admin', 2, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(3, 'Contabilidad', 1, 1, 'PointOfSaleRounded', 'Menu', '/contabilidad', 3, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(208, 'Tesorería', 3, 2, 'AccountBalanceIcon', 'Menu', '/contabilidad/tesoreria', 208, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(10, 'Activos Fijos', 1, 1, 'CarRepairRounded', 'Menu', '/activos-fijos2', 10, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(12, 'Talento Humano', 1, 1, 'Diversity3Rounded', 'Menu', '/talento-humano', 12, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(13, 'Comercial', 1, 1, 'PieChartRounded', 'Menu', '/comercial', 13, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(195, 'Pagos View', 193, 4, 'PaymentIcon', 'Page', '/contabilidad/tesoreria/pagos/edit-view', 195, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(164, 'Orden de Pedido', 7, 2, 'ShoppingCartRounded', 'Page', '/produccion/orden-pedido', 164, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(40, 'Plan Cuentas', 3, 2, 'SettingsApplicationsRounded', 'Page', '/contabilidad/plan-cuentas', 40, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(4, 'Adquisiciones', 1, 1, 'StorefrontRounded', 'Menu', '/adquisiciones', 4, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(9, 'Punto De Venta', 1, 1, 'AddShoppingCartRounded', 'Menu', '/pdv/facturacion/facturacion-pdv', 9, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(52, 'Gestion Documentos', 51, 3, 'ShoppingCartRounded', 'Menu', '/adquisiciones/compras-exterior/gestion-documentos', 52, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(6, 'Almacenamiento', 1, 1, 'InventoryRounded', 'Menu', '/almacenamiento', 6, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(30, 'Orden Compra New', 23, 4, NULL, 'Page', '/adquisiciones/compras-locales/orden-compra/new', 30, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(32, 'Recepción Compra New', 25, 4, NULL, 'Page', '/adquisiciones/compras-locales/recepcion-compra/new', 32, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(29, 'Solicitud Compra View', 24, 4, NULL, 'Page', '/adquisiciones/compras-locales/solicitud-compra/edit-view', 29, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(42, 'Transferencia', 6, 2, 'AssignmentReturnRounded ', 'Page', '/almacenamiento/transferencia', 42, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(33, 'Recepción Compra View', 25, 4, NULL, 'Page', '/adquisiciones/compras-locales/recepcion-compra/edit-view', 33, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(28, 'Solicitud Compra New', 24, 4, NULL, 'Page', '/adquisiciones/compras-locales/solicitud-compra/new', 28, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(36, 'Ficha Producto View', 19, 3, NULL, 'Page', '/almacenamiento/ficha-producto/edit-view', 36, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(31, 'Orden Compra View', 23, 4, NULL, 'Page', '/adquisiciones/compras-locales/orden-compra/edit-view', 31, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(27, 'Kardex', 6, 2, 'WarehouseRounded', 'Page', '/almacenamiento/kardex', 27, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(35, 'Ficha Producto New', 19, 3, NULL, 'Page', '/almacenamiento/ficha-producto/new', 35, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(22, 'Guía Remisión View', 41, 4, NULL, 'Page', '/almacenamiento/guias-de-remision/guia-remision/edit-view', 22, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(19, 'Ficha Producto', 6, 2, 'ShopTwoRounded', 'Page', '/almacenamiento/ficha-producto', 19, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(191, 'Usuarios View', 189, 4, 'PeopleIcon', 'Page', '/admin/menu-usuario/usuarios/edit-view', 191, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(8, 'Ventas', 1, 1, 'PeopleAltRounded', 'Menu', '/ventas', 8, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(66, 'Categorias Clientes', 8, 3, 'ShoppingCartRounded', 'Page', '/ventas/categroias-clientes', 66, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(7, 'Manufactura', 1, 1, 'FactoryRoundedIcon', 'Menu', '/produccion', 7, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(20, 'Menu', 21, 3, 'LowPriorityRounded', 'Page', '/menu', 20, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(163, 'Lista de Materiales', 7, 2, 'ShoppingBasketRounded', 'Page', '/produccion/lista-materiales', 163, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(190, 'Usuarios New', 189, 4, 'PeopleIcon', 'Page', '/admin/menu-usuario/usuarios/new', 190, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(67, 'Grupo Inventarios', 8, 3, 'ShoppingCartRounded', 'Page', '/ventas/grupo-inventarios', 67, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(47, 'Facturación Proveedor View', 48, 3, NULL, 'Page', '/adquisiciones/proveedores/factura-proveedor/edit-view', 47, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(68, 'Promociones', 8, 3, 'ShoppingCartRounded', 'Page', '/ventas/promociones', 68, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(50, 'Ficha Proveedor New', 45, 3, NULL, 'Page', '/adquisiciones/proveedores/ficha-proveedor/new', 50, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(49, 'Ficha Proveedor View', 45, 3, NULL, 'Page', '/adquisiciones/proveedores/ficha-proveedor/edit-view', 49, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(11, 'LIBRE1', NULL, 1, 'AccountBalanceRounded', 'Menu', '...2', 11, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(15, 'Parámetros', 12, 2, 'SettingsApplicationsRounded', 'Menu', '/talento-humano/parametros', 15, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(14, 'Ficha Empleado', 12, 2, 'AccountBoxRounded', 'Page', '/talento-humano/ficha-empleado', 14, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(16, 'Tipo Ingreso', 15, 3, 'InputRounded', 'Page', '/talento-humano/parametros/tipo-ingreso', 16, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(26, 'Asiento View', 192, 3, NULL, 'Page', '/contabilidad/asiento/view/:entryId', 26, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(202, 'Sesion', 21, 3, 'RecentActorsRounded', 'Page', '/admin/menu-usuario/sesion', 202, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(199, 'Roles New', 198, 4, NULL, 'Page', '/admin/menu-usuario/roles/new', 199, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(41, 'Guía Remisión', 94, 3, 'ManageAccountsRounded', 'Page', '/almacenamiento/guias-de-remision/guia-remision', 41, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(141, 'Transferencia View', 42, 3, 'ShoppingCartRounded', 'Page', '/almacenamiento/transferencia/edit-view', 141, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(198, 'Roles', 21, 3, 'TransferWithinAStationIcon', 'Page', '/admin/menu-usuario/roles', 198, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(21, 'Menu Usuario', 2, 2, 'RecentActorsRounded', 'Menu', '/menu-usuario', 21, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(200, 'Roles View', 198, 4, NULL, 'Page', '/admin/menu-usuario/roles/edit-view', 200, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(189, 'Usuarios', 21, 3, 'PeopleIcon', 'Page', '/admin/menu-usuario/usuarios', 189, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(64, 'Cambio Sucursales', 8, 2, 'ShoppingCartRounded', 'Page', '/ventas/cambio-sucursales', 64, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(65, 'Sucursales', 2, 2, 'WarehouseIcon', 'Page', '/admin/sucursales', 65, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(37, 'Devolución Compra', 18, 3, 'AssignmentReturnRounded ', 'Page', '/adquisiciones/compras-locales/devolucion-compra', 37, false);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(48, 'Facturación Proveedor', 34, 3, 'ReceiptRounded', 'Page', '/adquisiciones/proveedores/factura-proveedor', 48, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(45, 'Ficha Proveedor', 34, 3, 'LocalShippingRounded', 'Page', '/adquisiciones/proveedores/ficha-proveedor', 45, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(57, 'Retenciones', 34, 3, 'ReceiptRounded', 'Page', '/adquisiciones/proveedores/retenciones', 57, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(206, 'Sucursal New', 65, 3, NULL, 'Page', '/admin/sucursales/new', 206, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(207, 'Sucursal View', 65, 3, NULL, 'Page', '/admin/sucursales/edit-view', 207, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(194, 'Pagos New', 193, 4, 'PaymentIcon', 'Page', '/contabilidad/tesoreria/pagos/new', 194, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(193, 'Pagos', 208, 3, 'PaymentIcon', 'Page', '/contabilidad/tesoreria/pagos', 193, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(77, 'Sustentos Comprobantes', 73, 3, 'ShoppingCartRounded', 'Page', '/contabilidad/sustentos-comprobantes', 77, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(78, 'Posiciones Fiscales', 73, 3, 'ShoppingCartRounded', 'Page', '/contabilidad/posiciones-fiscales', 78, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(75, 'Comprobantes Autorizados', 73, 3, 'ShoppingCartRounded', 'Page', '/contabilidad/comprobantes-autorizados', 75, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(73, 'Contabilidad', 2, 2, 'BadgeIcon', 'Menu', '/admin/contabilidad', 73, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(79, 'Bancos', 73, 3, 'ShoppingCartRounded', 'Page', '/comprobantes/bancos', 79, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(76, 'Cuentas Bancarias', 73, 3, 'MoneyIcon', 'Page', '/admin/contabilidad/cuentas-bancarias', 76, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(80, 'Reprocesos', 2, 2, 'ShoppingCartRounded', 'Menu', '/reprocesos', 80, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(91, 'Impuestos', 15, 3, 'ShoppingCartRounded', 'Page', '/talento-humano/parametros/impuestos', 91, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(235, 'Nota Débito Cliente New', 234, 4, NULL, 'Page', '/ventas/clientes/nota-debito/new', 235, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(232, 'Nota Crédito Cliente New', 231, 4, NULL, 'Page', '/ventas/clientes/nota-credito/new', 232, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(236, 'Nota Débito Cliente View', 234, 4, NULL, 'Page', '/ventas/clientes/nota-debito/edit-view', 236, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(234, 'Nota Débito Cliente', 107, 3, 'ShoppingCartRounded', 'Page', '/ventas/clientes/nota-debito', 234, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(231, 'Nota Crédito Cliente', 107, 3, 'ShoppingCartRounded', 'Page', '/ventas/clientes/nota-credito', 231, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(233, 'Nota Crédito Cliente View', 231, 4, NULL, 'Page', '/ventas/clientes/nota-credito/edit-view', 233, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(136, 'Consultas Reportes', 116, 2, 'ShoppingCartRounded', 'Menu', '/bancos/reportes', 136, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(122, 'Confirmaciones', 116, 2, 'ShoppingCartRounded', 'Menu', '/bancos/confirmaciones', 122, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(123, 'Gastos por Liquidar', 122, 3, 'ShoppingCartRounded', 'Menu', '/bancos/confirmaciones/gastos-liquidar', 123, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(124, 'Proveedor', 123, 4, 'ShoppingCartRounded', 'Page', '/bancos/confirmaciones/gastos-liquidar/proveedor', 124, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(125, 'Transferencias Bancarias', 122, 3, 'ShoppingCartRounded', 'Menu', '/bancos/confirmaciones/transferencias-bancarias', 125, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(126, 'Proveedores', 125, 4, 'ShoppingCartRounded', 'Page', '/bancos/confirmaciones/transferencias-bancarias/proveedores', 126, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(127, 'Conciliciones', 122, 3, 'ShoppingCartRounded', 'Page', '/bancos/confirmaciones/conciliaciones', 127, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(128, 'Caja Chica', 116, 2, 'ShoppingCartRounded', 'Menu', '/bancos/caja-chica', 128, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(129, 'Pago Facturas', 128, 3, 'ShoppingCartRounded', 'Menu', '/bancos/caja-chica/pago-facturas', 129, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(130, 'Proveedores', 129, 4, 'ShoppingCartRounded', 'Page', '/bancos/caja-chica/pago-facturas/proveedores', 130, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(131, 'Pago Retenciones', 128, 3, 'ShoppingCartRounded', 'Menu', '/bancos/caja-chica/pago-retenciones', 131, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(132, 'Punto de Venta', 131, 4, 'ShoppingCartRounded', 'Page', '/bancos/caja-chica/pago-retenciones/punto-venta', 132, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(133, 'Pagos Manuales', 128, 3, 'ShoppingCartRounded', 'Page', '/bancos/caja-chica/pagos-manuales', 133, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(134, 'Trasnporte Clientes', 128, 3, 'ShoppingCartRounded', 'Page', '/bancos/caja-chica/transporte-clientes', 134, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(135, 'Confirmaciones', 128, 3, 'ShoppingCartRounded', 'Page', '/bancos/caja-chica/confirmaciones', 135, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(137, 'Depositos', 136, 3, 'ShoppingCartRounded', 'Page', '/bancos/reportes/depositos', 137, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(138, 'Tarjetas', 136, 3, 'ShoppingCartRounded', 'Page', '/bancos/reportes/tarjetas', 138, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(139, 'Cajas Chicas', 136, 3, 'ShoppingCartRounded', 'Page', '/bancos/reportes/cajas-chicas', 139, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(160, 'Consultas Reportes', 12, 2, 'ShoppingCartRounded', 'Menu', '/talento-humando/parametros/reportes', 160, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(143, 'Ingresos Egresos', 142, 3, 'ShoppingCartRounded', 'Page', '/talento-humando/novedades/ingresos-egresos', 143, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(144, 'Horas Extras', 142, 3, 'ShoppingCartRounded', 'Page', '/talento-humando/novedades/horas-extras', 144, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(145, 'Subsidios', 142, 3, 'ShoppingCartRounded', 'Page', '/talento-humando/novedades/subsidios', 145, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(146, 'Vacaiones', 142, 3, 'ShoppingCartRounded', 'Page', '/talento-humando/novedades/vacaiones', 146, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(147, 'Permisos', 142, 3, 'ShoppingCartRounded', 'Page', '/talento-humando/novedades/permisos', 147, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(166, 'Consultas Reportes', 7, 2, 'ShoppingCartRounded', 'Page', '/produccion/reportes', 166, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(149, 'Prestamos', 148, 3, 'ShoppingCartRounded', 'Page', '/talento-humando/descuentos/prestamos', 149, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(150, 'Cuentas por cobrar', 148, 3, 'ShoppingCartRounded', 'Page', '/talento-humando/descuentos/cuentas-cobrar', 150, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(152, 'Nomina Roles', 151, 3, 'ShoppingCartRounded', 'Menu', '/talento-humando/obligaciones-nomina/nomina-roles', 152, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(153, 'Produccion', 152, 4, 'ShoppingCartRounded', 'Page', '/talento-humando/obligaciones-nomina/nomina-roles/produccion', 153, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(74, 'Autorizaciones Sri', 73, 3, 'DescriptionIcon', 'Page', '/admin/contabilidad/autorizaciones-sri', 74, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(23, 'Orden Compra', 18, 3, 'ShoppingBasketRounded', 'Page', '/adquisiciones/compras-locales/orden-compra', 24, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(81, 'Reprocesos', 80, 3, 'ShoppingCartRounded', 'Page', '/reprocesos/reprocesos', 81, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(120, 'Depositos Tarjeta', 117, 3, 'ShoppingCartRounded', 'Page', '/bancos/ingresos-depositos/depositos-tarjeta', 120, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(157, 'RDEP', 156, 3, 'ShoppingCartRounded', 'Page', '/talento-humando/obligaciones-fiscales/rdep', 157, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(158, 'Impuestos a la renta', 156, 3, 'ShoppingCartRounded', 'Page', '/talento-humando/obligaciones-fiscales/impuestos-renta', 158, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(159, 'Gastos Personales', 156, 3, 'ShoppingCartRounded', 'Page', '/talento-humando/obligaciones-fiscales/gastos-personales', 159, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(161, 'Empleados', 160, 3, 'ShoppingCartRounded', 'Page', '/talento-humando/reportes/empleados', 161, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(162, 'Nomina Quincena', 160, 3, 'ShoppingCartRounded', 'Page', '/talento-humando/reportes/nomina-quincena', 162, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(155, 'Liquidaciones', 151, 3, 'ShoppingCartRounded', 'Page', '/talento-humando/obligaciones-nomina/liquidaciones', 155, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(154, 'Quincena', 151, 3, 'ShoppingCartRounded', 'Page', '/talento-humando/obligaciones-nomina/quincena', 154, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(165, 'Orden de Produccion', 7, 2, 'ShoppingCartRounded', 'Page', '/produccion/orden-produccion', 165, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(167, 'Lista Materiales New', 163, 3, 'ShoppingCartRounded', 'Page', '/produccion/lista-materiales/new', 167, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(70, 'Cajas Recaudadoras', 8, 3, 'ShoppingCartRounded', 'Page', '/ventas/cajas-recaudadoras', 70, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(72, 'Formas de Pago', 71, 3, 'PaymentsIcon', 'Page', '/admin/pagos/formas-pago', 72, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(71, 'Pagos', 2, 2, 'PaymentIcon', 'Menu', '/admin/pagos', 71, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(83, 'LIBRE 123', NULL, 2, 'ShoppingCartRounded', 'Menu', '...T', 83, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(18, 'Compras Locales', 4, 2, 'LocalShippingRounded', 'Menu', '/adquisiciones/proveedores', 18, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(34, 'Proveedores', 4, 2, 'StorefrontRounded', 'Menu', '/adquisiciones/compras-locales', 44, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(176, 'Retenciones View', 57, 3, 'ShoppingBasketRounded', 'Page', '/adquisiciones/proveedores/retenciones/edit-view', 176, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(177, 'Lista Empaque', 53, 4, 'ShoppingBasketRounded', 'Page', '/adquisiciones/compras-exterior/distribucion-importacion/lista-empaque', 177, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(104, 'LIBRE 33', NULL, 0, 'ShoppingCartRounded', 'Page', '/..*9', 104, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(111, 'LIBRE', NULL, 0, 'ShoppingCartRounded', 'Page', '..*98', 111, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(106, 'Consultas Reportes', 100, 3, 'ShoppingCartRounded', 'Page', '/ventas/facturacion/reportes', 106, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(98, 'Consultas Reportes', 6, 2, 'ShoppingCartRounded', 'Page', '/almacenamiento/reportes', 98, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(94, 'Guías De Remisión', 6, 2, 'DescriptionIcon', 'Menu', '/almacenamiento/guias-de-remision', 94, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(101, 'Manejo Caja', 100, 3, 'ShoppingCartRounded', 'Page', '/ventas/facturacion/manejo-caja', 101, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(95, 'Guía Remisión New', 41, 4, NULL, 'Page', '/almacenamiento/guias-de-remision/guia-remision/new', 95, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(97, 'Punto de Venta', NULL, 1, NULL, 'Page', 'Crear url', 97, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(237, 'Cobros Clientes', 107, 3, 'ShoppingCartRounded', 'Page', '/ventas/clientes/cobros-clientes', 237, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(238, 'Cobros Clientes View ', 237, 3, NULL, 'Page', '/ventas/clientes/cobros-clientes/edit-view', 238, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(102, 'Cotizacion Cliente', 100, 3, 'ShoppingCartRounded', 'Page', '/ventas/facturacion/cotizacion-cliente', 102, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(103, 'Facturacion PDV', 100, 3, 'ShoppingCartRounded', 'Page', '/ventas/facturacion/facturacion-pdv', 103, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(109, 'Categoria Clientes', 107, 3, 'ShoppingCartRounded', 'Page', '/ventas/clientes/categoria-clientes', 109, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(112, 'Reprogramar Carteta', 107, 3, 'ShoppingCartRounded', 'Page', '/ventas/clientes/reprogramar-cartera', 112, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(113, 'Retencion Clientes', 107, 3, 'ShoppingCartRounded', 'Page', '/ventas/clientes/retencion-clientes', 113, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(114, 'Analisis 360', 107, 3, 'ShoppingCartRounded', 'Page', '/ventas/clientes/analisis', 114, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(115, 'Cupones Clientes', 107, 3, 'ShoppingCartRounded', 'Page', '/ventas/clientes/cupones-clientes', 115, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(116, 'Bancos', 1, 1, 'PointOfSaleRounded', 'Menu', '/bancos', 116, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(117, 'Ingresos Depositos', 116, 2, 'ShoppingCartRounded', 'Menu', '/bancos/ingresos-depositos', 117, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(108, 'Ficha Clientes', 107, 3, 'ShoppingCartRounded', 'Page', '/ventas/clientes/ficha-clientes', 108, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(168, 'Lista Materiales View', 163, 3, 'ShoppingCartRounded', 'Page', '/produccion/lista-materiales/edit-view', 168, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(169, 'Orden de Pedido New', 164, 3, 'ShoppingCartRounded', 'Page', '/produccion/orden-pedido/new', 169, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(170, 'Orden de Pedido View', 164, 3, 'ShoppingCartRounded', 'Page', '/produccion/orden-pedido/edit-view', 170, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(171, 'Orden de Produccion New', 165, 3, 'ShoppingCartRounded', 'Page', '/produccion/orden-produccion/new', 171, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(172, 'Orden de Produccion View', 165, 3, 'ShoppingCartRounded', 'Page', '/produccion/orden-produccion/edit-view', 172, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(55, 'Consultas Reportes', 51, 3, 'ShoppingCartRounded', 'Menu', '/adquisiciones/compras-exterior/consultas-reportes', 55, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(174, 'Orden Compra Exterior New', 173, 5, NULL, 'Page', '/adquisiciones/compras-exterior/distribucion-importacion/orden-compra/new', 174, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(56, 'Lista Pedido', 52, 4, 'ShoppingCartRounded', 'Page', '/adquisiciones/compras-exterior/gestion-documentos/lista-pedido', 56, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(59, 'Lista Pedido View', 56, 5, 'ShoppingCartRounded', 'Page', '/adquisiciones/compras-exterior/gestion-documentos/lista-pedido/edit-view', 59, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(58, 'Lista Pedido New', 56, 5, 'ShoppingCartRounded', 'Page', '/adquisiciones/compras-exterior/gestion-documentos/lista-pedido/new', 58, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(61, 'Apertura Importacion New', 60, 5, 'ShoppingCartRounded', 'Page', '/adquisiciones/compras-exterior/gestion-documentos/apertura-importacion/new', 61, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(60, 'Apertura Importacion', 52, 4, 'ShoppingCartRounded', 'Page', '/adquisiciones/compras-exterior/gestion-documentos/apertura-importacion', 60, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(175, 'Orden Compra Exterior View', 173, 5, NULL, 'Page', '/adquisiciones/compras-exterior/distribucion-importacion/orden-compra/edit-view', 175, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(173, 'Orden Compra Exterior', 53, 4, 'ShoppingBasketRounded', 'Page', '/adquisiciones/compras-exterior/distribucion-importacion/orden-compra', 173, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(62, 'Apertura Importacion View', 60, 5, 'ShoppingCartRounded', 'Page', '/adquisiciones/compras-exterior/gestion-documentos/apertura-importacion/edit-view', 62, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(53, 'Distribucion Importacion', 51, 3, 'ShoppingCartRounded', 'Menu', '/adquisiciones/compras-exterior/distribucion-importacion', 53, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(54, 'Costos Importacion', 51, 3, 'ShoppingCartRounded', 'Menu', '/adquisiciones/compras-exterior/costos-importacion', 54, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(96, 'Transportistas', 94, 3, 'AssignmentReturnRounded', 'Page', '/almacenamiento/guias-remision/transportistas', 96, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(140, 'Transferencia New', 42, 3, 'ShoppingCartRounded', 'Page', '/almacenamiento/transferencia/new', 140, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(93, 'Ajustes Inventario', 6, 2, 'AssignmentReturnRounded', 'Page', '/almacenamiento/ajustes-inventario', 93, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(105, 'Egreso caja', 100, 3, 'ShoppingCartRounded', 'Page', '/ventas/facturacion/egresos-caja', 105, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(110, 'Cuentas Por Cobrar', 107, 3, 'ShoppingCartRounded', 'Page', '/ventas/clientes/cuentas-por-cobrar', 110, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(118, 'Depositos', 117, 3, 'ShoppingCartRounded', 'Page', '/bancos/ingresos-depositos/depositos', 118, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(178, 'Lista Empaque New', 177, 5, NULL, 'Page', '/adquisiciones/compras-exterior/distribucion-importacion/lista-empaque/new', 178, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(179, 'Lista Empaque View', 177, 5, NULL, 'Page', '/adquisiciones/compras-exterior/distribucion-importacion/lista-empaque/edit-view', 179, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(182, 'Liquidación View', 180, 5, NULL, 'Page', '/adquisiciones/compras-exterior/costos-importacion/liquidacion/edit-view', 182, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(181, 'Liquidación New', 180, 5, NULL, 'Page', '/adquisiciones/compras-exterior/costos-importacion/liquidacion/new', 181, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(180, 'Liquidación', 54, 4, 'ShoppingBasketRounded', 'Page', '/adquisiciones/compras-exterior/costos-importacion/liquidacion', 180, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(183, 'Nota Credito New', 92, 4, 'ShoppingBasketRounded', 'Page', '/adquisiciones/proveedores/notas-credito-debito/new', 183, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(92, 'Notas de Credito Debito', 34, 3, 'ReceiptRounded', 'Page', '/adquisiciones/proveedores/notas-credito-debito', 92, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(186, 'Nota Credito View', 92, 4, 'ShoppingBasketRounded', 'Page', '/adquisiciones/proveedores/notas-credito-debito/edit-view', 186, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(100, 'Facturacion', 8, 2, 'ShoppingCartRounded', 'Menu', '/ventas/facturacion', 100, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(107, 'Clientes', 8, 2, 'ShoppingCartRounded', 'Menu', '/ventas/clientes', 107, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(188, 'Ficha Clientes View ', 108, 3, NULL, 'Page', '/ventas/clientes/ficha-clientes/edit-view', 188, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(187, 'Ficha Clientes New ', 108, 3, NULL, 'Page', '/ventas/clientes/ficha-clientes/new', 187, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(69, 'Descuentos Especiales', 8, 3, 'ShoppingCartRounded', 'Page', '/ventas/descuentos-especiales', 69, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(201, 'Menu Usuario', 21, 3, 'RecentActorsRounded', 'Page', '/admin/menu-usuario/menu-usuario', 201, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(205, 'Facturacion PDV View', 103, 4, NULL, 'Page', '/ventas/facturacion/facturacion-pdv/edit-view', 205, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(204, 'Facturacion PDV New', 103, 4, NULL, 'Page', '/ventas/facturacion/facturacion-pdv/new', 204, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(209, 'Ventas', 2, 2, 'MonetizationOnIcon', 'Menu', '/admin/ventas', 209, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(230, 'Manejo Caja View', 101, 4, 'ShoppingCartRounded', 'Page', '/ventas/facturacion/manejo-caja/edit-view', 230, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(213, 'Grupo Inventarios', 209, 3, 'FormatListBulletedIcon', 'Page', '/admin/ventas/grupo-inventarios', 213, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(229, 'Manejo Caja New', 101, 4, 'ShoppingCartRounded', 'Page', '/ventas/facturacion/manejo-caja/new', 229, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(228, 'Cuentas Por Pagar', 208, 3, 'PaymentIcon', 'Page', '/contabilidad/tesoreria/cuentas-por-pagar', 228, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(210, 'Categoría Clientes', 209, 3, 'FolderSharedIcon', 'Page', '/admin/ventas/categoria-clientes', 210, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(215, 'Grupo Inventarios View', 213, 4, NULL, 'Page', '/admin/ventas/grupo-inventarios/edit-view', 215, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(211, 'Categoria Clientes New', 210, 4, NULL, 'Page', '/admin/ventas/categoria-clientes/new', 211, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(212, 'Categoria Clientes View', 210, 4, NULL, 'Page', '/admin/ventas/categoria-clientes/edit-view', 212, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(214, 'Grupo Inventarios New', 213, 4, NULL, 'Page', '/admin/ventas/grupo-inventarios/new', 214, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(219, 'Cajas Recaudadoras', 209, 3, 'PointOfSaleRounded', 'Page', '/admin/ventas/cajas-recaudadoras', 219, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(220, 'Cajas Recaudadoras New', 219, 4, NULL, 'Page', '/admin/ventas/cajas-recaudadoras/new', 220, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(221, 'Cajas Recaudadoras View', 219, 4, NULL, 'Page', '/admin/ventas/cajas-recaudadoras/edit-view', 221, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(222, 'Subgrupo', 209, 3, 'FormatListBulletedIcon', 'Page', '/admin/ventas/subgrupo', 222, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(223, 'Periodo Contable', 73, 3, 'FormatListBulletedIcon', 'Page', '/admin/contabilidad/periodo-contable', 223, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(224, 'Documentos Autorizados', 73, 3, 'DocumentScannerIcon', 'Page', '/admin/contabilidad/documentos-autorizados', 224, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(225, 'Cuenta Bancaria New', 76, 4, NULL, 'Page', '/admin/contabilidad/cuentas-bancarias/new', 225, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(226, 'Cuenta Bancaria View', 76, 4, NULL, 'Page', '/admin/contabilidad/cuentas-bancarias/edit-view', 226, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(227, 'Catalogo Bancos', 73, 3, 'LocationCityIcon', 'Page', '/admin/contabilidad/catalogo-bancos', 227, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(46, 'Facturación Proveedor New', 48, 3, NULL, 'Page', '/adquisiciones/proveedores/factura-proveedor/new', 46, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(5, 'MENU LIBRE ', NULL, 1, 'LocalShippingRounded', 'Menu', '....', 5, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(142, 'Novedades', 12, 2, 'ShoppingCartRounded', 'Menu', '/talento-humando/parametros/novedades', 142, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(85, 'Departamento/Cargo', 15, 3, 'ShoppingCartRounded', 'Page', '/talento-humano/parametros/departamento-cargo', 85, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(88, 'Modalidad Contrato', 15, 3, 'ShoppingCartRounded', 'Page', '/talento-humano/parametros/modalidad-contrato', 88, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(86, 'Estado Civil', 15, 3, 'ShoppingCartRounded', 'Page', '/talento-humano/parametros/estado-civil', 86, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(87, 'Discapacidad', 15, 3, 'ShoppingCartRounded', 'Page', '/talento-humano/parametros/discapacidad', 87, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(148, 'Descuentos', 12, 2, 'ShoppingCartRounded', 'Menu', '/talento-humando/parametros/descuentos', 148, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(90, 'Subsidios', 15, 3, 'ShoppingCartRounded', 'Page', '/talento-humano/parametros/subsidios', 90, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(156, 'Obligaciones Fiscales', 12, 2, 'ShoppingCartRounded', 'Menu', '/talento-humando/parametros/obligaciones-fiscales', 156, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(151, 'Obligaciones Nomina', 12, 2, 'ShoppingCartRounded', 'Menu', '/talento-humando/parametros/obligaciones-nomina', 151, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(84, 'Tipo de Ingreso/Egreso', 15, 3, 'ShoppingCartRounded', 'Page', '/talento-humano/parametros/tipo-ingreso-egreso', 84, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(89, 'Contratos', 15, 3, 'ShoppingCartRounded', 'Page', '/talento-humano/parametros/contratos', 89, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(192, 'Asiento', 3, 2, 'ChairIcon', 'Page', '/contabilidad/asiento', 192, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(25, 'Recepción Compra', 18, 3, 'ShoppingBasketRounded', 'Page', '/adquisiciones/compras-locales/recepcion-compra', 25, true);
INSERT INTO erp."menu"
(id_menu, nombre_menu, padre_menu, nivel_menu, icon_menu, component_menu, url_menu, orden_menu, estado_menu)
VALUES(24, 'Solicitud Compra', 18, 3, 'ShoppingBasketRounded', 'Page', '/adquisiciones/compras-locales/solicitud-compra', 23, true);

-- 2. Insertar menu_usuario para el usuario admin (asignar todos los menus a id_usuario = 1)
INSERT INTO erp."menu_usuario" ("id_menu", "id_usuario", "estado_menu_usuario")
SELECT "id_menu", 1, true FROM erp."menu";
