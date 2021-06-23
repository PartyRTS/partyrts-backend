package stud.team.pwsbackend.config;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.util.MultiValueMap;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import stud.team.pwsbackend.security.SimpleAuthentication;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.List;

@Configuration
@Slf4j
public class WebSocketMessageBrokerConfig implements WebSocketMessageBrokerConfigurer {

    @Value("${jwt.token.secret}")
    private String secret;

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new ChannelInterceptor() {
            @Override
            public Message<?> preSend(@NonNull Message<?> message, @NonNull MessageChannel channel) {
                MessageHeaders headers = message.getHeaders();
                StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
                //noinspection rawtypes
                MultiValueMap multiValueMap = headers.get(StompHeaderAccessor.NATIVE_HEADERS, MultiValueMap.class);

                if (multiValueMap == null) {
                    return message;
                }

                assert accessor != null;
                if (StompCommand.CONNECT.equals(accessor.getCommand())) {
                    if (!multiValueMap.containsKey("token")) {
                        return message;
                    }
                    try {
                        //noinspection unchecked
                        String token = ((List<String>) multiValueMap.get("token")).get(0);
                        log.info("received token: {}", token);

                        JwtParser parser = Jwts.parserBuilder()
                                .setSigningKey(getSigningKey())
                                .build();

                        var jws = parser.parseClaimsJws(token);
                        long userId = Integer.parseInt(jws.getBody().getSubject());

                        log.info("received userId from token: {}", userId);
                        var principal = new SimpleAuthentication(userId);
                        accessor.setUser(principal);

                    } catch (Exception e) {
                        //ignore
                    }
                }
                return message;
            }
        });
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(this.secret);
        return new SecretKeySpec(keyBytes, SignatureAlgorithm.HS256.getJcaName());
    }
}
