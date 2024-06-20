package com.learning.journaling.module.wallet;

import com.learning.journaling.module.wallet.dto.WalletRequest;
import com.learning.journaling.module.wallet.dto.WalletResponse;
import com.learning.journaling.module.wallet.mapper.WalletMapper;
import com.learning.journaling.module.wallet.module.Wallet;
import com.learning.journaling.module.wallet.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wallet")
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody WalletRequest request){
        walletService.create(request);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WalletResponse> getById(@PathVariable String id){
        return ResponseEntity.ok(WalletMapper.mapToResponse(walletService.getById(id)));
    }

    @GetMapping("/list")
    public ResponseEntity<List<WalletResponse>> getAll(){
        return ResponseEntity.ok(WalletMapper.mapToResponseList(walletService.getList()));
    }

    @GetMapping("/page")
    public ResponseEntity<Page<WalletResponse>> getPage(Pageable pageable, @RequestParam(required = false) String search){
        Page<Wallet> walletPage = walletService.getPage(pageable, search);
        return ResponseEntity.ok(new PageImpl<>(WalletMapper.mapToResponseList(walletPage.getContent()), pageable, walletPage.getTotalElements()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateById(@PathVariable String id, @RequestBody WalletRequest request){
        walletService.update(id, request);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable String id){
        walletService.delete(id);
        return ResponseEntity.ok(null);
    }
}
