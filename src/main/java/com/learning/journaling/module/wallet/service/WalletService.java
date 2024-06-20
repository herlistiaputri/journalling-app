package com.learning.journaling.module.wallet.service;

import com.learning.journaling.configuration.exception.BaseException;
import com.learning.journaling.module.user.model.User;
import com.learning.journaling.module.user.service.UserService;
import com.learning.journaling.module.wallet.dto.WalletRequest;
import com.learning.journaling.module.wallet.mapper.WalletMapper;
import com.learning.journaling.module.wallet.module.Category;
import com.learning.journaling.module.wallet.module.Wallet;
import com.learning.journaling.module.wallet.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepository walletRepository;
    private final CategoryService categoryService;
    private final MongoTemplate mongoTemplate;


    public void create(WalletRequest request){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Category category = categoryService.getById(request.getCategoryId());
        Wallet wallet = WalletMapper.mapToModel(request, new Wallet());
        wallet.setCategory(category);
        wallet.setUser(user);
        walletRepository.save(wallet);
    }

    public Wallet getById(String id){
        return walletRepository.findById(id).orElseThrow(() -> new BaseException(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), "Id not found"));
    }

    public List<Wallet> getList(){
        return walletRepository.findAll();
    }

    public Page<Wallet> getPage(Pageable pageable, String search){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Query query = new Query().with(pageable);
        List<Criteria> criteria = new ArrayList<>();
        criteria.add(Criteria.where("user").is(user));
        if(search != null && !search.isBlank()){
            criteria.add(Criteria.where("name").regex(".*"+search+"*.", "i"));
        }
        query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[0])));
        return PageableExecutionUtils.getPage(
                mongoTemplate.find(query, Wallet.class), pageable,
                () -> mongoTemplate.count(query.skip(0).limit(0), Wallet.class));
    }

    public void update(String id, WalletRequest request) {
        Wallet wallet = getById(id);
        WalletMapper.mapToModel(request, wallet);
    }

    public void delete(String id){
        Wallet wallet = getById(id);
        walletRepository.delete(wallet);
    }
}
