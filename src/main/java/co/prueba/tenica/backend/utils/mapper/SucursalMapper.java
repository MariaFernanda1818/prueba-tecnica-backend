package co.prueba.tenica.backend.utils.mapper;

import co.prueba.tenica.backend.dto.SucursalDto;
import co.prueba.tenica.backend.entity.SucursalModel;
import org.mapstruct.Mapper;

/**
 * Mapper de MapStruct para convertir entre el DTO SucursalDto
 * y su entidad asociada SucursalModel.
 */
@Mapper(componentModel = "spring")
public interface SucursalMapper {

    /**
     * Convierte un DTO {@link SucursalDto} a la entidad {@link SucursalModel}.
     *
     * @param dto DTO de sucursal a convertir.
     * @return entidad lista para persistir en la base de datos.
     */
    SucursalModel toModel(SucursalDto dto);
}
