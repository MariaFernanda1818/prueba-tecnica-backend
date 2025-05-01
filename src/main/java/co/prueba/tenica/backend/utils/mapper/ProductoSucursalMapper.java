package co.prueba.tenica.backend.utils.mapper;

import co.prueba.tenica.backend.dto.ProductoSucursalDto;
import co.prueba.tenica.backend.entity.ProductoSucursalModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductoSucursalMapper {

    ProductoSucursalModel toModel(ProductoSucursalDto productoSucursalDto);

}
