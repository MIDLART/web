package org.example.web.controllers;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.web.services.BackupService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class BackupController {
  private final BackupService backupService;

  @GetMapping("/admin/backup")
  public void loadBackup(HttpServletResponse response) {
    String backupPath = backupService.getBackupPath();

    response.setHeader("Content-disposition", "attachment;filename=" + backupPath);
    response.setContentType("application/octet-stream");

    try {
      backupService.loadBackup(response.getOutputStream());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @PostMapping("/admin/backup")
  public String uploadBackup(MultipartFile backupFile) {
    if (backupFile == null) {
      return "redirect:/admin";
    }

    backupService.restoreFromBackupFile(backupFile);

    return "redirect:/admin";
  }

}
