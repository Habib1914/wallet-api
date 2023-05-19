package br.com.limaisaias.walletapi.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Response<T> {

    private T data;

    private List<String> errors;
}
