package co.prueba.tenica.backend.repository.callback;

import co.prueba.tenica.backend.entity.ProductoModel;
import org.springframework.data.r2dbc.mapping.event.BeforeConvertCallback;
import org.springframework.data.relational.core.sql.SqlIdentifier;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.security.SecureRandom;

@Component
public class ProductoModelBeforeConvert implements BeforeConvertCallback<ProductoModel> {

    private static final SecureRandom RANDOM = new SecureRandom();
    private static final char[] ALPHANUM =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

    @Override
    public Mono<ProductoModel> onBeforeConvert(ProductoModel entity, SqlIdentifier table) {
        if (entity.getCodigo() == null || entity.getCodigo().isBlank()) {
            entity.setCodigo(generarCodigoAlfanumerico());
        }
        return Mono.just(entity);
    }

    private String generarCodigoAlfanumerico() {
        StringBuilder sb = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            sb.append(ALPHANUM[RANDOM.nextInt(ALPHANUM.length)]);
        }
        return sb.toString();
    }
}