package product.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * セキュリティを設定する場合、WebSecurityConfiguraterAdapterを継承して
 * クラスを作成する。
 *
 * @Configuration
 * @EnableWebSecurity Spring Securityの機能を有効にする
 *
 * セキュリティの設定は、configure(http)とconfigure(auth)メソッドに記載する
 * configure(http) httpリクエストの設定
 * configure(auth) ユーザの設定
 * @author keita
 *
 */

@Configuration
@EnableWebSecurity // Spring Securityの機能を有効にする
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		// パスワードの暗号化に、bcrypt（ビー・クリプト）を使用します
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				// 認証リクエストの設定
				.authorizeRequests()
				// 認証の必要があるように設定
				.anyRequest().authenticated()
				.and()
				// フォームベース認証の設定
				.formLogin()
				// ログイン成功後のデフォルトページを指定
				.defaultSuccessUrl("liveList", true);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
				// メモリ内認証を設定
				.inMemoryAuthentication()
				// "user"を追加
				.withUser("quickusr")
				// "password"をBCryptで暗号化
				.password(passwordEncoder().encode("password"))
				// 権限（ロール）を追加
				.authorities("ROLE_USER");
	}
}
