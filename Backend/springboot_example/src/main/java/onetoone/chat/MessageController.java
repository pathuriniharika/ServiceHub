//package onetoone.chat;
//
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiResponse;
//import io.swagger.annotations.ApiResponses;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Optional;
//
//
//@Api(value = "Swagger2DemoAdmin", description = "REST APIs related to Message")
//@RestController
//@RequestMapping("/api")
//
//
//public class MessageController {
//
//    @Autowired
//    private MessageRepository messageRepository;
//    @ApiOperation(value = "messege marked as seen", response = EmbeddedLdapProperties.Credential.class)
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Success|OK"),
//            @ApiResponse(code = 404, message = "Not Found")})
//    @PostMapping("/messages/{messageId}/markAsSeen")
//    public ResponseEntity<String> markMessageAsSeen(@PathVariable Long messageId) {
//        Optional<Message> message = messageRepository.findById(messageId);
//        if (message.isPresent()) {
//            message.get().setSeen(true);
//            messageRepository.save(message.get());
//            return ResponseEntity.ok("Message marked as seen.");
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Message not found.");
//        }
//    }
//
//
//
//    public ResponseEntity<Message> saveMessage(@RequestBody Message message) {
//        try {
//            // Assuming you have a way to determine if the message is bold (from the frontend)
//            boolean isBold = message.getIsBold(); // Get bold information from the message
//
//            // Set the isBold property of the message
//            message.setIsBold(isBold);
//
//            // Save the message to the repository
//            Message savedMessage = messageRepository.save(message);
//
//            return ResponseEntity.ok(savedMessage);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        }
//    }
//
//
//
//}
//
//
