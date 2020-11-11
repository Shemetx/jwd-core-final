package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.util.InputService;

public class CrewCriteriaService {

    public static Criteria<CrewMember> createCriteria() {
        Long id = null;
        String name = null;
        Role role = null;
        Rank rank = null;
        Boolean isReady = null;
        int choose = Integer.MAX_VALUE;
        while (choose != 0) {
            printAvailableOptions();
            System.out.println("Choose menu item: ");
            choose = InputService.getInt();
            switch (choose) {
                case 1:
                    System.out.println("Enter id: ");
                    id = (long) InputService.getInt();
                    break;
                case 2:
                    System.out.println("Enter name: ");
                    name = InputService.getString();
                    break;
                case 3:
                    System.out.println("Choose role: ");
                    role = InputService.getRole();
                    break;
                case 4:
                    System.out.println("Choose rank: ");
                    rank = InputService.getRank();
                    break;
                case 5:
                    System.out.println("Choose is ready for next mission: ");
                    isReady = InputService.isReadyForNextMission();
                    break;
                default:
                    break;

            }
        }
        Long finalId = id;
        String finalName = name;
        Role finalRole = role;
        Rank finalRank = rank;
        Boolean finalIsReady = isReady;
        return new CrewMemberCriteria.CrewBuilder() {{
            id(finalId);
            name(finalName);
            role(finalRole);
            rank(finalRank);
            isReadyForNextMissions(finalIsReady);
        }}.build();
    }

    public static Criteria<CrewMember> getCriteriaByRole(Role role, Boolean isReady) {
        return new CrewMemberCriteria.CrewBuilder() {{
            role(role);
            isReadyForNextMissions(isReady);
        }}.build();
    }

    public static Criteria<CrewMember> getCriteriaById(Long id) {
        return new CrewMemberCriteria.CrewBuilder() {{
            id(id);
        }}.build();
    }

    private static void printAvailableOptions() {
        System.out.println("Choose which criteria you want: ");
        System.out.println("1 - ID");
        System.out.println("2 - Name");
        System.out.println("3 - Role");
        System.out.println("4 - Rank");
        System.out.println("5 - is Ready for next mission");
        System.out.println("0 - Stop to choose");
    }
}
