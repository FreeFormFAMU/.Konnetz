package com.connetz.connetz.models.post;

import com.connetz.connetz.util.Debug.TimestampDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;
import com.google.firebase.database.annotations.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class APost {

    @DocumentId
    protected @Nullable String id;

    protected String user_id;

    public String title;

    protected String content;

    @JsonDeserialize(using = TimestampDeserializer.class)
    protected  Timestamp created_at;

    @JsonDeserialize(using = TimestampDeserializer.class)
    protected Timestamp updated_at;

    /*public class JsonSerializationConfig {

        public static ObjectMapper getObjectMapper() {
            ObjectMapper objectMapper = new ObjectMapper();
            SimpleModule module = new SimpleModule();
            module.addSerializer(Timestamp.class, new CustomTimestampSerializer());
            module.addDeserializer(Timestamp.class, new CustomTimestampDeserializer());
            objectMapper.registerModule(module);
            return objectMapper;
        }
    }*/
}
