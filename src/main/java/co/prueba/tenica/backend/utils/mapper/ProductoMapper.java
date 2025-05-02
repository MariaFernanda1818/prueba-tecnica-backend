package co.prueba.tenica.backend.utils.mapper;

import co.prueba.tenica.backend.dto.ProductoDto;
import co.prueba.tenica.backend.entity.ProductoModel;
import org.mapstruct.Mapper;

/**
 * Mapper de MapStruct para convertir entre la entidad R2DBC de Producto
 * y su DTO correspondiente.
 * Proporciona métodos para mapear de DTO a entidad.
 */
@Mapper(componentModel = "spring")
public interface ProductoMapper {

    /**
     * Convierte un DTO {@link ProductoDto} a la entidad {@link ProductoModel}.
     *
     * @param dto DTO de producto a convertir.
     * @return entidad lista para persistir en la base de datos.
     */
    ProductoModel toModel(ProductoDto dto);

}
