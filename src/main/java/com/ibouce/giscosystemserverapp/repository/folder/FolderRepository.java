package com.ibouce.giscosystemserverapp.repository.folder;

import com.ibouce.giscosystemserverapp.entity.folder.FolderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FolderRepository extends JpaRepository<FolderModel, Long> {

    FolderModel findByUserIdAndName(Long id, String name);

    FolderModel findByNameAndParent(String name, Long id);

    List<FolderModel> findByParent(Long id);

    List<FolderModel> findByUserId(Long id);

    FolderModel findFolderByParent(Long id);

    FolderModel findFolderById(Long id);

    FolderModel findParentById(Long id);

    Optional<FolderModel> findById(Long id);

}
