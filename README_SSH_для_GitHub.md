## **Подробная инструкция по настройке SSH для GitHub**

**🔹 Для пользователя GitHub-репозитория**

---

## **1. Генерация SSH-ключа**

### **Windows / macOS / Linux**
1. **Откройте терминал** (Git Bash на Windows, Terminal на macOS/Linux).    
2. **Выполните команду** (замените `your_email@example.com` на почту, привязанную к GitHub):
```bash
ssh-keygen -t ed25519 -C "your_email@example.com"
```
   - Нажмите `Enter` для сохранения в стандартную папку (`~/.ssh/id_ed25519`). 
   - Можно задать пароль для ключа (рекомендуется для безопасности).
    
3. **Запустите `ssh-agent` и добавьте ключ:**
```bash
eval "$(ssh-agent -s)"
ssh-add ~/.ssh/id_ed25519
```
   _(Если файл называется иначе, укажите его имя.)_
 
---
## **2. Добавление SSH-ключа в GitHub**
1. **Скопируйте публичный ключ:**

  - **Windows:**
```bash
cat ~/.ssh/id_ed25519.pub | clip
```
	
  - **macOS:**
```bash
pbcopy < ~/.ssh/id_ed25519.pub
```
	
  - **Linux:**
```bash
xclip -sel clip < ~/.ssh/id_ed25519.pub
```
_(Если `xclip` нет, можно просто открыть файл вручную.)_

2. **Добавьте ключ в GitHub:**    
    - Перейдите в **Settings → SSH and GPG keys → New SSH key**.        
    - Вставьте скопированный ключ в поле `Key`.        
    - Назовите ключ (например, `My Laptop`) и нажмите **Add SSH key**.        

---
## **3. Проверка работоспособности SSH**

1. **Проверьте подключение к GitHub:**
```bash
ssh -T git@github.com
```
   - Если всё ок, увидите:
```text
Hi username! You've successfully authenticated, but GitHub does not provide shell access.
```
   - Если ошибка — проверьте, что ключ добавлен в `ssh-agent` (`ssh-add -l`).
    
2. **Клонируйте репозиторий по SSH:**
```bash
git clone git@github.com:ваш-юзернейм/ваш-репозиторий.git
```
   _(Если до этого клонировали по HTTPS — переключите URL на SSH: `git remote set-url origin git@github.com:user/repo.git`)_
    
3. **Проверка `git push/pull`:**
    - Сделайте тестовый коммит и попробуйте запушить:
```bash
git add .
git commit -m "Test SSH connection"
git push
```
   - Если ошибок нет — ключ работает! 🎉

---
## **4. Настройка SSH в IntelliJ IDEA**

1. **Откройте IDEA → Settings → Version Control → Git**.
2. Убедитесь, что путь к `git` указан верно.    
3. **При клонировании репозитория выберите SSH-URL** (не HTTPS).    
4. **При пуше IDEA должна автоматически использовать SSH.**    

---

---
### **🔹 Что делать, если SSH не работает?**
- **Проверьте ключи:**
```bash
ls ~/.ssh  # Должны быть id_ed25519 и id_ed25519.pub
```

- **Перезапустите `ssh-agent`:**
```bash
eval "$(ssh-agent -s)"
ssh-add ~/.ssh/id_ed25519
```

- **Проверьте URL репозитория:**
```bash
git remote -v
```
_(Должен быть `git@github.com:...`, а не `https://...`.)_

---

