package com.javahub.rag.service;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class RagPromptSynthesizerService {
    public record RagPrompt(String systemContext, String query, int chunksIncluded) {}
    public RagPrompt buildContext(String userQuery) {
        String docs = "[Source: JDK21 Spec] Virtual Threads are lightweight user-mode threads managed by the runtime rather than the OS kernel.";
        String system = "Answer truthfully using ONLY this context:\n" + docs;
        return new RagPrompt(system, userQuery, 1);
    }
}
