import java.io.OptionalDataException; /**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        // Подсчет количества ненулевых элементов
        int countNonNull = 0;
        for (Resume element : storage) {
            if (element != null) {
                countNonNull++;
            }
        }
        // Создание нового массива без null элементов
        Resume[] newArray = new Resume[countNonNull];
        int index = 0;
        for (Resume element : storage) {
            if (element != null) {
                newArray[index++] = element;
            }
        }
        // Присваиваем newArray в storage
        storage = newArray;
    }

    void  save(Resume userID) {
        if (userID != null){
            Resume[] newArray = new Resume[storage.length + 1];
            //Сохраняем r в начало нового массива
            newArray[0] = userID;
            // Копируем содержимое storage в newArray начиная с индекса 1
            System.arraycopy(storage, 0, newArray, 1, storage.length);
            // Присваиваем newArray в storage
            storage = newArray;
        }
    }

    Resume get(String uuid) {
        //Ищем совпадающие значения uuid и элементов storage, если такие имеются, то возвращаем
        for (Resume result : storage){
            if(result!=null && uuid.equals(result.uuid)){
                return result;
            }
        }
        // Если объект с указанным uuid не найден возвращает null
        return null;
    }

    void delete(String uuid) {
        int arrayLength = 0;

        for (Resume result : storage) {
            if (!uuid.equals(result.uuid)) {
                arrayLength++;
            }
        }
        Resume[] newArray = new Resume[arrayLength];
        int index = 0;
        //Ищем совпадающие значения uuid и элементов storage
        for (Resume result : storage) {
            if (result != null && !uuid.equals(result.uuid)) {
                newArray[index++] = result;
            }
        }

        // Присваиваем newArray в storage
        storage = newArray;
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
        return storage.length;
    }
}