import java.io.OptionalDataException;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i < storage.length; i++) {
            storage[i] = null;
        }
    }

    void save(Resume userID) {
        if (userID != null) {
            // Сдвигаем существующие элементы вправо
            for (int i = storage.length - 1; i > 0; i--) {
                storage[i] = storage[i - 1];
            }
            // Сохраняем новый элемент в начало массива
            storage[0] = userID;
        }
    }

    Resume get(String uuid) {
        //Ищем совпадающие значения uuid и элементов storage, если такие имеются, то возвращаем
        for (Resume result : storage) {
            if (result != null && uuid.equals(result.uuid)) {
                return result;
            }
        }
        // Если объект с указанным uuid не найден возвращает null
        return null;
    }

    void delete(String uuid) {
        int arrayLength = 0;
        // Подсчет количества элементов, не совпадающих с заданным uuid
        for (Resume result : storage) {
            if (result == null || !uuid.equals(result.uuid)) {
                arrayLength++;
            }
        }
        // Сдвигаем элементы влево, убирая те, у которых uuid совпадает
        int currentIndex = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null && uuid.equals(storage[i].uuid)) {
                continue; // Пропускаем элементы, которые нужно удалить
            }
            storage[currentIndex++] = storage[i];
        }
        // Заполняем "лишние" элементы массива null
        for (int i = currentIndex; i < storage.length; i++) {
            storage[i] = null;
        }
    }


    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] allResumes = new Resume[storage.length];
        // Копируем все элементы из storage в allResumes
        System.arraycopy(storage, 0, allResumes, 0, storage.length);
        //возвращаем массив с такими же значениями, как и в storage
        return allResumes;
    }

    int size() {
        //Возвращаем длину массива
        int count = 0;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                count += 1;
            }
        }
        return count;
    }
}