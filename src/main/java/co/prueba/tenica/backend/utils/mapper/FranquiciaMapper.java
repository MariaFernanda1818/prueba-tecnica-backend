package co.prueba.tenica.backend.utils.mapper;


import co.prueba.tenica.backend.dto.FranquiciaDto;
import co.prueba.tenica.backend.entity.FranquiciaModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FranquiciaMapper {


    FranquiciaDto toDto(FranquiciaModel model);

    FranquiciaModel toModel(FranquiciaDto dto);

    List<FranquiciaDto> modelToDto(List<FranquiciaModel> model);


}
