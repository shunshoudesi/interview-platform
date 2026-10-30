package com.interview.modules.llmprovider.controller;

import com.interview.common.result.Result;
import com.interview.modules.llmprovider.dto.AsrConfigDTO;
import com.interview.modules.llmprovider.dto.AsrConfigRequest;
import com.interview.modules.llmprovider.dto.CreateProviderRequest;
import com.interview.modules.llmprovider.dto.DefaultProviderDTO;
import com.interview.modules.llmprovider.dto.ProviderDTO;
import com.interview.modules.llmprovider.dto.ProviderTestResult;
import com.interview.modules.llmprovider.dto.TtsConfigDTO;
import com.interview.modules.llmprovider.dto.TtsConfigRequest;
import com.interview.modules.llmprovider.dto.UpdateProviderRequest;
import com.interview.modules.llmprovider.service.LlmProviderConfigService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/llm-provider")
@RequiredArgsConstructor
@Slf4j
public class LlmProviderController {

  private final LlmProviderConfigService configService;

  @GetMapping("/list")
  public Result<List<ProviderDTO>> listProviders() {
    return Result.success(configService.listProviders());
  }

  @GetMapping("/{id}")
  public Result<ProviderDTO> getProvider(@PathVariable String id) {
    return Result.success(configService.getProvider(id));
  }

  @PostMapping
  public Result<Void> createProvider(@RequestBody @Valid CreateProviderRequest request) {
    configService.createProvider(request);
    return Result.success();
  }

  @PutMapping("/{id}")
  public Result<Void> updateProvider(@PathVariable String id,
      @RequestBody UpdateProviderRequest request) {
    configService.updateProvider(id, request);
    return Result.success();
  }

  @DeleteMapping("/{id}")
  public Result<Void> deleteProvider(@PathVariable String id) {
    configService.deleteProvider(id);
    return Result.success();
  }

  @PostMapping("/{id}/test")
  public Result<ProviderTestResult> testProvider(@PathVariable String id) {
    return Result.success(configService.testProvider(id));
  }

  @PostMapping("/reload")
  public Result<Void> reloadProviders() {
    configService.reloadProviders();
    return Result.success();
  }

  @GetMapping("/default-provider")
  public Result<DefaultProviderDTO> getDefaultProvider() {
    return Result.success(configService.getDefaultProvider());
  }

  @PutMapping("/default-provider")
  public Result<Void> updateDefaultProvider(@RequestBody DefaultProviderDTO request) {
    configService.updateDefaultProvider(request);
    return Result.success();
  }

  @PutMapping("/default-embedding-provider")
  public Result<Void> updateDefaultEmbeddingProvider(@RequestBody DefaultProviderDTO request) {
    configService.updateDefaultEmbeddingProvider(request);
    return Result.success();
  }

  // ===== Voice ASR/TTS Config =====

  @GetMapping("/voice/asr")
  public Result<AsrConfigDTO> getAsrConfig() {
    return Result.success(configService.getAsrConfig());
  }

  @PutMapping("/voice/asr")
  public Result<Void> updateAsrConfig(@RequestBody AsrConfigRequest request) {
    configService.updateAsrConfig(request);
    return Result.success();
  }

  @GetMapping("/voice/tts")
  public Result<TtsConfigDTO> getTtsConfig() {
    return Result.success(configService.getTtsConfig());
  }

  @PutMapping("/voice/tts")
  public Result<Void> updateTtsConfig(@RequestBody TtsConfigRequest request) {
    configService.updateTtsConfig(request);
    return Result.success();
  }

  @PostMapping("/voice/asr/test")
  public Result<ProviderTestResult> testAsrConfig() {
    return Result.success(configService.testAsrConfig());
  }
}
