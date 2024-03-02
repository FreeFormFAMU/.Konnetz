package com.connetz.connetz.models.messages;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;
import com.google.protobuf.util.Timestamps;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;
import java.text.ParseException;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AMessages {
    // Setting the architect of the AMessages abstract class with
    // Protected variables
    @DocumentId
    // Ask if we need to make this nullable
    protected @Nullable String messageId;
    private String content;
    protected Timestamp timestamp;

    // Timestamp void function to be able to create time stamps
    public void setTimestampAt(String createdAt) throws ParseException {
        this.timestamp = Timestamp.fromProto(Timestamps.parse(createdAt));
    }
}
