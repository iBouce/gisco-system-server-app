package com.ibouce.giscosystemserverapp.controller.folder;

import com.ibouce.giscosystemserverapp.entity.folder.FolderModel;
import com.ibouce.giscosystemserverapp.service.folder.FolderService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/folder")
@AllArgsConstructor
public class FolderController {

    @Autowired
    FolderService folderService;

    @PostMapping("/save")
    public FolderModel saveFolderToUser(@RequestBody FolderModel folder) throws Exception {
        return folderService.saveFolderToUser(folder);
    }

    @PostMapping("/get")
    public FolderModel getFolderByName(@RequestBody FolderModel folder) {
        return folderService.getFolderForUser(folder);
    }

    @GetMapping("/tree/{id}")
    public List<FolderModel> getUserFolders(@PathVariable Long id) {
        return folderService.getTreeOfFolder(id);
    }

    @GetMapping("/node/{id}")
    public List<FolderModel> getSubFoldersFolder(@PathVariable Long id) {
        return folderService.getNodesOfFolder(id);
    }

    @GetMapping("/parent/{id}")
    public FolderModel getParentOfFolder(@PathVariable Long id) {
        return folderService.getParentOfFolder(id);
    }

    @GetMapping("/path/{id}")
    public String getFolderPath(@PathVariable Long id) {
        return folderService.getFolderPath(id);
    }

}
