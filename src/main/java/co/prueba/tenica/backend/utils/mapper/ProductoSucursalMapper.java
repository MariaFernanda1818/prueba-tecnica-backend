package co.prueba.tenica.backend.utils.mapper;

import co.prueba.tenica.backend.dto.ProductoSucursalDto;
import co.prueba.tenica.backend.entity.ProductoSucursalModel;
import org.mapstruct.Mapper;

/**
 * Mapper de MapStruct para convertir entre el DTO ProductoSucursalDto
 * y su entidad asociada ProductoSucursalModel.
 */
@Mapper(componentModel = "spring")
public interface ProductoSucursalMapper {

    /**
     * Convierte un DTO {@link ProductoSucursalDto} a la entidad {@link ProductoSucursalModel}.
     *
     * @param productoSucursalDto DTO de asociación producto-sucursal a convertir.
     * @return entidad lista para persistir en la base de datos.
     */
    ProductoSucursalModel toModel(ProductoSucursalDto productoSucursalDto);
}
