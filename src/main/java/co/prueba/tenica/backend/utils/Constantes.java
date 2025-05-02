package co.prueba.tenica.backend.utils;

/**
 * Clase utilitaria que agrupa todas las constantes utilizadas en el backend
 * del sistema, incluyendo rutas de los endpoints, mensajes de validación,
 * mensajes de éxito y error para operaciones de Franquicia, Producto y Sucursal.
 * <p>
 * Esta clase no debe ser instanciada.
 * </p>
 *
 * @author
 */
public class Constantes {

    /**
     * Constructor privado para evitar la instanciación de esta clase utilitaria.
     */
    private Constantes() {
        // Previene instanciación
    }

    // ==================== Rutas - Franquicia ====================

    /** Ruta base para todas las operaciones de franquicia. */
    public static final String FRANQUICIA_BASE_PATH = "/franquicia";

    /** Ruta para crear una nueva franquicia. */
    public static final String CREATE_PATH = "/crear";

    /** Ruta para modificar el nombre de una franquicia existente. */
    public static final String MODIFY_NAME_PATH = "/modificar-nombre";

    // ==================== Rutas - Producto ====================

    /** Ruta base para operaciones sobre productos. */
    public static final String PRODUCTO_BASE_PATH = "/producto";

    /** Ruta para modificar el stock de un producto. */
    public static final String MODIFY_STOCK_PATH = "/modificar-stock";

    /** Ruta para eliminar un producto. */
    public static final String DELETE_PATH = "/eliminar";

    /** Ruta para consultar los productos con stock máximo por sucursal. */
    public static final String MAX_STOCK_PATH = "/productos-stock-max";

    // ==================== Rutas - Sucursal ====================

    /** Ruta base para todas las operaciones de sucursal. */
    public static final String SUCURSAL_BASE_PATH = "/sucursal";

    /** Ruta para crear una nueva sucursal. */
    public static final String SUCURSAL_CREATE_PATH = "/crear";

    /** Ruta para modificar el nombre de una sucursal. */
    public static final String SUCURSAL_MODIFY_NAME_PATH = "/modificar-nombre";

    // ==================== Validaciones - Franquicia ====================

    /** Mensaje de error cuando el nombre de la franquicia está vacío. */
    public static final String MSG_NOMBRE_FRANQUICIA_NOT_BLANK =
            "El nombre de la franquicia no puede estar vacío";

    /** Mensaje de error cuando el nombre de la franquicia es null. */
    public static final String MSG_NOMBRE_FRANQUICIA_NOT_NULL =
            "El nombre de la franquicia no puede ser null";

    /** Mensaje de error cuando el nombre de la franquicia excede 100 caracteres. */
    public static final String MSG_NOMBRE_FRANQUICIA_MAX_100 =
            "El nombre de la franquicia no puede superar los 100 caracteres";

    // ==================== Validaciones - Producto ====================

    /** Mensaje de error cuando el nombre del producto es obligatorio. */
    public static final String MSG_NOMBRE_PRODUCTO_OBLIGATORIO =
            "El nombre del producto es obligatorio";

    /** Mensaje de error cuando el nombre del producto supera los 100 caracteres. */
    public static final String MSG_NOMBRE_PRODUCTO_MAX_100 =
            "El nombre no puede exceder 100 caracteres";

    /** Mensaje de error cuando el código del producto es obligatorio. */
    public static final String MSG_CODIGO_OBLIGATORIO = "El código de producto es obligatorio";

    /** Mensaje de error cuando el código del producto supera los 10 caracteres. */
    public static final String MSG_CODIGO_MAX_10 = "El código no puede exceder 10 caracteres";

    /** Mensaje de error cuando el código está vacío. */
    public static final String MSG_CODIGO_NOT_EMPTY = "Debe tener algun contenido el codigo";

    /** Mensaje de error cuando el nuevo stock es obligatorio. */
    public static final String MSG_NUEVO_STOCK_OBLIGATORIO = "El nuevo stock es obligatorio";

    /** Mensaje de error cuando el nuevo stock es negativo. */
    public static final String MSG_NUEVO_STOCK_NO_NEGATIVO = "El nuevo stock no puede ser negativo";

    // ==================== Validaciones - Sucursal ====================

    /** Mensaje de error cuando el nombre de la sucursal es obligatorio. */
    public static final String MSG_NOMBRE_SUCURSAL_OBLIGATORIO = "El nombre de la sucursal es obligatorio";

    /** Mensaje de error cuando el nombre de la sucursal supera los 100 caracteres. */
    public static final String MSG_NOMBRE_SUCURSAL_MAX_100 = "El nombre no puede exceder 100 caracteres";

    /** Mensaje de error cuando el ID de la franquicia es obligatorio. */
    public static final String MSG_ID_FRANQUICIA_OBLIGATORIO = "El id de la franquicia es obligatorio";

    /** Mensaje de error cuando el ID es obligatorio. */
    public static final String MSG_ID_OBLIGATORIO = "Debe tener obligatoriamente un id";

    /** Mensaje de error cuando el nombre está vacío. */
    public static final String MSG_NOMBRE_NOT_EMPTY = "Debe tener algun contenido el nombre";

    /** Mensaje de error cuando el nombre supera los 100 caracteres. */
    public static final String MSG_NOMBRE_MAX_100 = "El nombre de la franquicia no puede superar los 100 caracteres";

    /** Mensaje de error cuando el ID de la sucursal es obligatorio. */
    public static final String MSG_ID_SUCURSAL_OBLIGATORIO = "El id de la sucursal es obligatorio";

    // ==================== Mensajes de operación - Consulta ====================

    /** Mensaje exitoso al consultar productos con stock máximo. */
    public static final String MSG_CONSULTA_MAX_STOCK_EXITOSA =
            "Se consultaron correctamente todos los productos por sucursal con max stock";

    // ==================== Mensajes de operación - Franquicia ====================

    /** Mensaje de éxito al crear una franquicia. */
    public static final String MSG_FRANQUICIA_CREADA_EXITOSA = "Se creó correctamente la franquicia";

    /** Mensaje de error al intentar crear una franquicia. */
    public static final String MSG_FRANQUICIA_CREACION_ERROR = "Hubo un error al crear la franquicia";

    /** Mensaje de éxito al modificar el nombre de una franquicia. */
    public static final String MSG_FRANQUICIA_MODIFICADA_EXITOSA = "Se modificó correctamente el nombre de la franquicia";

    /** Mensaje de error al modificar el nombre de una franquicia. */
    public static final String MSG_FRANQUICIA_MODIFICACION_ERROR = "Hubo un error al modificar el nombre de la franquicia";

    /** Log de error técnico al modificar una franquicia. */
    public static final String LOG_ERROR_MODIFICAR_FRANQUICIA = "Error modificando franquicia: {}";

// ==================== Mensajes de operación - Producto ====================

    /** Mensaje de éxito al crear un producto. */
    public static final String MSG_PRODUCTO_CREADO_EXITOSO = "Se creó correctamente el producto";

    /** Mensaje de error al intentar crear un producto. */
    public static final String MSG_PRODUCTO_CREACION_ERROR = "Hubo un error al crear el producto";

    /** Mensaje de éxito al eliminar un producto de forma global. */
    public static final String MSG_PRODUCTO_ELIMINADO_GLOBAL = "Producto %s eliminado correctamente";

    /** Mensaje de éxito al eliminar un producto específico de una sucursal. */
    public static final String MSG_PRODUCTO_ELIMINADO_SUCURSAL = "Producto %s eliminado de la sucursal %d correctamente";

    /** Mensaje de error al eliminar un producto de forma global. */
    public static final String MSG_PRODUCTO_ELIMINACION_ERROR_GLOBAL = "Error eliminando el producto %s";

    /** Mensaje de error al eliminar un producto de una sucursal específica. */
    public static final String MSG_PRODUCTO_ELIMINACION_ERROR_SUCURSAL = "Error eliminando el producto %s de la sucursal %d";

    /** Mensaje de éxito al modificar el nombre de un producto. */
    public static final String MSG_PRODUCTO_NOMBRE_MODIFICADO_EXITOSO = "Se modificó correctamente el nombre del producto";

    /** Mensaje de error al modificar el nombre de un producto. */
    public static final String MSG_PRODUCTO_NOMBRE_MODIFICACION_ERROR = "Hubo un error al modificar el nombre del producto";

    /** Mensaje de éxito al modificar el stock de un producto. */
    public static final String MSG_PRODUCTO_STOCK_MODIFICADO_EXITOSO = "Se modificó correctamente el stock del producto";

    /** Mensaje de error al modificar el stock de un producto. */
    public static final String MSG_PRODUCTO_STOCK_MODIFICACION_ERROR = "Hubo un error al modificar el stock del producto";

    /** Log de error técnico al crear un producto. */
    public static final String LOG_ERROR_CREADO_PRODUCTO = "Error creando producto completo";

    /** Log de error técnico al eliminar un producto. */
    public static final String LOG_ERROR_ELIMINAR_PRODUCTO = "Error eliminando producto={} sucursal={}";

    /** Log de error técnico al modificar el nombre de un producto. */
    public static final String LOG_ERROR_MODIFICAR_NOMBRE_PRODUCTO = "Error modificando nombre de producto: {}";

    /** Log de error técnico al modificar el stock de un producto. */
    public static final String LOG_ERROR_MODIFICAR_STOCK_PRODUCTO = "Error modificando stock de producto: {}";

// ==================== Mensajes de operación - Sucursal ====================

    /** Mensaje de éxito al crear una sucursal. */
    public static final String MSG_SUCURSAL_CREADA_EXITOSA = "Se creó correctamente la sucursal";

    /** Mensaje de error al intentar crear una sucursal. */
    public static final String MSG_SUCURSAL_CREACION_ERROR = "Hubo un error al crear la sucursal";

    /** Mensaje de éxito al modificar el nombre de una sucursal. */
    public static final String MSG_SUCURSAL_NOMBRE_MODIFICADO_EXITOSO = "Se modificó correctamente el nombre de la sucursal";

    /** Mensaje de error al modificar el nombre de una sucursal. */
    public static final String MSG_SUCURSAL_NOMBRE_MODIFICACION_ERROR = "Hubo un error al modificar el nombre de la sucursal";

    /** Log de error técnico al crear una sucursal. */
    public static final String LOG_ERROR_CREADO_SUCURSAL = "Error creando sucursal";

    /** Log de error técnico al modificar el nombre de una sucursal. */
    public static final String LOG_ERROR_MODIFICAR_NOMBRE_SUCURSAL = "Error modificando nombre de sucursal: {}";

    // ==================== Validaciones generales ====================

    /**
     * Mensaje genérico para indicar errores de validación en el cuerpo de la petición.
     */
    public static final String MSG_VALIDATION_ERRORS_BODY = "Errores de validación";

    /**
     * Mensaje genérico para indicar errores de validación en parámetros de la petición.
     */
    public static final String MSG_VALIDATION_ERRORS_PARAMS = "Errores de validación de parámetros";

    /**
     * Mensaje de error cuando ocurre un problema al crear una franquicia.
     */
    public static final String MSG_ERROR_CREAR_FRANQUICIA = "Error creando franquicia";

    // ==================== Rutas ====================

    /**
     * Ruta para asociar un producto a una o varias sucursales.
     */
    public static final String AGREGAR_PRODUCTO_SUCURSAL_PATH = "/agregar-producto-sucursal";

    // ==================== DTO InAgregarProductoSucursalDto ====================

    /**
     * Mensaje de error cuando el código de producto no es proporcionado.
     */
    public static final String MSG_CODIGO_PRODUCTO_OBLIGATORIO = "El código de producto es obligatorio";

    /**
     * Mensaje de error cuando el código de producto excede la longitud máxima.
     */
    public static final String MSG_CODIGO_PRODUCTO_MAX = "El código de producto no puede exceder 10 caracteres";

    /**
     * Mensaje de error cuando no se especifica ninguna sucursal.
     */
    public static final String MSG_SUCURSALES_OBLIGATORIO = "Debe especificar al menos una sucursal";

    /**
     * Mensaje de error cuando la lista de sucursales está vacía.
     */
    public static final String MSG_SUCURSALES_NO_VACIO = "La lista de sucursales no puede estar vacía";

    // ==================== AgregarProductoSucursalService ====================

    /**
     * Mensaje de error cuando el producto no existe en el sistema.
     */
    public static final String MSG_PRODUCTO_NO_ENCONTRADO =
            "El producto enviado no existe, por favor coloca uno que exista";

    /**
     * Formato de mensaje de error cuando la combinación producto–sucursal ya existe.
     * <p>
     * Usa String.format con idSucursal y codigoProducto.
     */
    public static final String MSG_COMBINACION_EXISTE =
            "La combinación sucursal=%d y producto=%s ya existe";

    /**
     * Mensaje de éxito cuando todas las sucursales han sido procesadas correctamente.
     */
    public static final String MSG_TODAS_SUCURSALES_OK =
            "Todas las sucursales procesadas con éxito";

}
