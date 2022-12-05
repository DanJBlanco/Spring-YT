package academy.yt.store.serviceproduct.util;

import lombok.*;

import java.util.List;
import java.util.Map;

@Getter @Setter @Builder
public class ErrorMessage {

    private String code;

    private List<Map<String, String>> messages;


}
