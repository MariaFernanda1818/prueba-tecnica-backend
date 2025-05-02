package co.prueba.tenica.backend.utils.mapper;

import co.prueba.tenica.backend.dto.FranquiciaDto;
import co.prueba.tenica.backend.entity.FranquiciaModel;
import org.mapstruct.Mapper;

/**
 * Mapper de MapStruct para convertir entre la entidad JPA de Franquicia
 * y su DTO correspondiente.
 */
@Mapper(componentModel = "spring")
public interface FranquiciaMapper {

    /**
     * Convierte una entidad {@link FranquiciaModel} a su DTO {@link FranquiciaDto}.
     *
     * @param model entidad de franquicia a convertir.
     * @return DTO con los datos de la franquicia.
     */
    FranquiciaDto toDto(FranquiciaModel model);

    /**
     * Convierte un DTO {@link FranquiciaDto} a la entidad {@link FranquiciaModel}.
     *
     * @param dto DTO de franquicia a convertir.
     * @return entidad lista para persistir en la base de datos.
     */
    FranquiciaModel toModel(FranquiciaDto dto);
}
