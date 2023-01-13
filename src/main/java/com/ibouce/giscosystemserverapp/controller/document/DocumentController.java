package com.ibouce.giscosystemserverapp.controller.document;

import com.ibouce.giscosystemserverapp.entity.document.DocumentModel;
import com.ibouce.giscosystemserverapp.entity.folder.FolderModel;
import com.ibouce.giscosystemserverapp.entity.user.UserModel;
import com.ibouce.giscosystemserverapp.service.document.DocumentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/document")
@AllArgsConstructor
public class DocumentController {

    @Autowired
    DocumentService documentService;

    @PostMapping("/save")
    public DocumentModel saveDocumentToFolder(@RequestParam("files") List<MultipartFile> files, @RequestParam(value = "user") UserModel user, @RequestParam(value = "folder") FolderModel folder) throws Exception {
        return documentService.saveDocumentToFolder(files, user, folder);
    }

    @GetMapping("/folder/{id}")
    public List<DocumentModel> getDocumentsFolder(@PathVariable Long id) {
        return documentService.getDocumentsOfFolder(id);
    }


}
